package brussels.bric.iot.acceptance;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;

/**
 * The Rest API acceptance tests main executor.
 * 
 * @author Adi Bajramov
 * 
 * @version 0.1.0
 * @since 0.1.0
 */
@RunWith(CucumberWithSerenity.class)
@CucumberOptions(features = "src/test/resources/features")
public class RestAcceptanceTest {

}
