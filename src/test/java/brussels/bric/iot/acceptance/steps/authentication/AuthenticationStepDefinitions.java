package brussels.bric.iot.acceptance.steps.authentication;

import brussels.bric.iot.acceptance.steps.serenity.support.BasicAuthenticationSupportSteps;
import brussels.bric.iot.acceptance.steps.serenity.support.BasicAuthenticationSupportSteps.BasicAuth;

import cucumber.api.java.en.Given;
import net.thucydides.core.annotations.Steps;

/**
 * The authentication steps definitions for rest-services acceptance tests.
 *
 * @author Adi Bajramov
 *
 * @version 0.1.0
 * @since 0.1.0
 */
public class AuthenticationStepDefinitions {

    @Steps
    private BasicAuthenticationSupportSteps basicAuthenticationSupport;

    @Given("^an authenticated actuator manager$")
    public void authenticatedActuatorManager() {
        basicAuthenticationSupport.initActuatorManagerAuth(new BasicAuth("management", "management"));
    }

}
