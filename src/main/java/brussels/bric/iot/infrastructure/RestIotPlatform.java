package brussels.bric.iot.infrastructure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import brussels.bric.iot.configuration.ApplicationProperties;
import brussels.bric.iot.domain.model.SoundData;
import brussels.bric.iot.service.IotPlatformGateway;

/**
 * @author abajramov
 * @since 3/26/18
 */
@Component
public class RestIotPlatform implements IotPlatformGateway {

    // -------------------------------------------------------------------------------Constant(s)---
    private static final Logger LOGGER = LoggerFactory.getLogger(RestIotPlatform.class);
    private static final String AUTH_FLOW_FORMAT =
            "{\"AuthFlow\":\"USER_PASSWORD_AUTH\", \"AuthParameters\":{\"PASSWORD\":\"%s\", \"USERNAME\":\"%s\"}, \"ClientId\":\"%s\"}";
    private static final String MEASURE_FORMAT   =
            "{\"deviceId\":\"%s\",\"date\":\"%s\",\"Laeq15\":\"%s\",\"Laeq60\":\"%s\",\"Lceq15\":\"%s\",\"Lceq60\":\"%s\"}";

    // -----------------------------------------------------------------------------Property(ies)---
    private final String authenticationUrl;
    private final String password;
    private final String user;
    private final String clientId;
    private final String deviceId;
    private final String url;

    // ----------------------------------------------------------------------------Constructor(s)---
    public RestIotPlatform(ApplicationProperties applicationProperties) {
        authenticationUrl = applicationProperties.getIotPlatform().getAuthUrl();
        password = applicationProperties.getIotPlatform().getPassword();
        user = applicationProperties.getIotPlatform().getUser();
        clientId = applicationProperties.getIotPlatform().getClientId();
        deviceId = applicationProperties.getIotPlatform().getDeviceId();
        url = applicationProperties.getIotPlatform().getUrl();
    }

    // -------------------------------------------------------------------------------Override(s)---
    @Override
    public void sendSoundData(SoundData soundData) {
        String authenticationToken = authenticate();

        if (authenticationToken != null) {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.set("Authorization", authenticationToken);
            httpHeaders.set("Content-Type", "application/json");
            HttpEntity<String> httpEntity = new HttpEntity<>(
                    String.format(MEASURE_FORMAT, deviceId, soundData.getIso8601Date(), soundData.getLaeq15(),
                            soundData.getLaeq60(), soundData.getLceq15(), soundData.getLceq60()), httpHeaders);
            try {
                ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, httpEntity, String.class);
                LOGGER.info("Received {} response", response.getStatusCode().toString());
            } catch (Exception e) {
                LOGGER.error("Exception during exchange", e);
            }
        }
    }

    private String authenticate() {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        String token = null;
        httpHeaders.set("X-Amz-Target", "AWSCognitoIdentityProviderService.InitiateAuth");
        httpHeaders.set("Content-Type", "application/x-amz-json-1.1");
        HttpEntity<String> httpEntity =
                new HttpEntity(String.format(AUTH_FLOW_FORMAT, password, user, clientId), httpHeaders);

        ResponseEntity<String> response =
                restTemplate.exchange(authenticationUrl, HttpMethod.POST, httpEntity, String.class);
        String stringResponse = response.getBody();
        int idTokenIndex = stringResponse.indexOf("IdToken");
        if (idTokenIndex > 0) {
            String idTokenSubstring = stringResponse.substring(idTokenIndex, stringResponse.indexOf(",", idTokenIndex));
            String[] idTokenSplit = idTokenSubstring.replaceAll(" ", "").replaceAll("\"", "").split(":");
            if (idTokenSplit.length >= 2) {
                token = idTokenSplit[1];
            }
        }

        return token;
    }

    // ---------------------------------------------------------------------------------Method(s)---

    // -----------------------------------------------------------------------Getter(s)/Setter(s)---
}