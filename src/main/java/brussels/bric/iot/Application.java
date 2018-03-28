package brussels.bric.iot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import brussels.bric.iot.configuration.ApplicationProperties;

/**
 * The application launcher.
 *
 * @author Adi Bajramov
 *
 * @version 0.1.0
 * @since 0.1.0
 */
@SpringBootApplication
@EnableConfigurationProperties(ApplicationProperties.class)
public class Application {

    /**
     * Launches the application.
     *
     * @param args
     *            the arguments to run the application.
     *
     * @throws Exception
     *             if an exception occurred while running the application.
     */
    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }

}
