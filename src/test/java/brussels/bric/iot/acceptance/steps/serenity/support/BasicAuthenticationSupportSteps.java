package brussels.bric.iot.acceptance.steps.serenity.support;

import static net.serenitybdd.rest.SerenityRest.rest;

import io.restassured.specification.RequestSpecification;
import lombok.Data;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;

/**
 * Supporting steps to manage authentication, by adding an {@code Authorization} header with BASIC to rest requests.
 *
 * @author Adi Bajramov
 *
 * @version 0.2.0
 * @since 0.2.0
 */
@SuppressWarnings("serial")
public class BasicAuthenticationSupportSteps extends ScenarioSteps {

    private static final String AUTH_VARIABLE = "BASIC_AUTH";

    /**
     * Initializes the BASIC auth to be used during REST calls.
     */
    public void initActuatorManagerAuth(BasicAuth auth) {
        currentAuth(auth);
    }

    /**
     * Initializes a {@link RequestSpecification} with {@code Authorization} header set with JWT.
     */
    @Step
    public RequestSpecification restWithAuth() {
        BasicAuth auth = currentAuth();
        if (currentAuth() != null) {
            return rest().auth().preemptive().basic(auth.userName, auth.password);
        }
        return rest();
    }

    private BasicAuth currentAuth() {
        return Serenity.sessionVariableCalled(AUTH_VARIABLE);
    }

    private void currentAuth(BasicAuth token) {
        Serenity.setSessionVariable(AUTH_VARIABLE).to(token);
    }

    @Data
    public static class BasicAuth {
        private final String userName;
        private final String password;
    }

}
