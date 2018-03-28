package brussels.bric.iot.acceptance.steps.actuator;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.runtime.java.StepDefAnnotation;
import net.thucydides.core.annotations.Steps;

import brussels.bric.iot.acceptance.steps.serenity.ActuatorServiceClientSteps;

/**
 * The health steps definitions for rest-services acceptance tests.
 *
 * @author Adi Bajramov
 *
 * @version 0.1.0
 * @since 0.1.0
 */
@StepDefAnnotation
public class HealthStepDefinitions {

    @Steps
    private ActuatorServiceClientSteps actuatorServiceClient;

    @Given("^a running service$")
    public void runningService() {
        // nothing to do here
    }

    @When("^I check the service health$")
    public void checkServiceHealth() {
        actuatorServiceClient.callHealthCheckService();
    }

    @Then("^the service health is ok$")
    public void serviceHealthIsOk() {
        actuatorServiceClient.assertResponseIsOk();
    }

    @Then("^the health detail is available$")
    public void healthDetailIsAvailable() {
        actuatorServiceClient.assertDiskUsageIsAvailable();
    }

}
