package brussels.bric.iot.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

/**
 * Configuration properties to configure the application.
 *
 * @author Adi Bajramov
 *
 * @version 0.1.0
 * @since 0.1.0
 */
@Data
@ConfigurationProperties(prefix = "application")
public class ApplicationProperties {

    private Long interval;
    private String measureLocation;
    private IotPlatform iotPlatform;

    @Data
    public static class IotPlatform {
        private String authUrl;
        private String url;
        private String user;
        private String password;
        private String clientId;
        private String deviceId;
    }
}
