package brussels.bric.iot.acceptance.steps.serenity;

import static net.serenitybdd.rest.SerenityRest.then;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.springframework.http.HttpStatus.OK;

import org.springframework.boot.actuate.health.Status;

import brussels.bric.iot.acceptance.steps.serenity.support.BasicAuthenticationSupportSteps;

import io.restassured.response.ValidatableResponse;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.steps.ScenarioSteps;

/**
 * The actuator rest-service client steps definition.
 *
 * @author Adi Bajramov
 *
 * @version 0.1.0
 * @since 0.1.0
 */
@SuppressWarnings("serial")
public class ActuatorServiceClientSteps extends ScenarioSteps {

    @Steps
    private BasicAuthenticationSupportSteps authenticationSupportSteps;

    private ValidatableResponse             healthCheckResponse;

    @Step("Call health check service")
    public void callHealthCheckService() {
        healthCheckResponse = authenticationSupportSteps.restWithAuth().get("/management/health").then();
    }

    @Step("Assert rest-service response is OK")
    public void assertResponseIsOk() {
        then().statusCode(OK.value());
    }

    @Step("Assert disk usage detail is available")
    public void assertDiskUsageIsAvailable() {
        healthCheckResponse.assertThat().body("diskSpace.status", equalTo(Status.UP.getCode()));
    }

}
