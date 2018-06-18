package brussels.bric.iot;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import brussels.bric.iot.configuration.ApplicationProperties;
import brussels.bric.iot.infrastructure.FileSoundRepository;

/**
 * The application launcher test.
 *
 * @author Adi Bajramov
 * @version 0.1.0
 * @since 0.1.0
 */
@Tag("integration")
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ApplicationTest {
    
    @Test
    public void contextLoads() {
        // Ensure that the production application can start
    }
    
    @Configuration
    static class ApplicationTestConfiguration {
        
        @Primary
        @Bean
        public FileSoundRepository fileSoundRepository() throws IOException {
            ApplicationProperties applicationProperties = new ApplicationProperties();
            applicationProperties.setMeasureLocation(File.createTempFile("temporaryFile", ".csv").getAbsolutePath());
            return new FileSoundRepository(applicationProperties);
        }
    }
    
}
