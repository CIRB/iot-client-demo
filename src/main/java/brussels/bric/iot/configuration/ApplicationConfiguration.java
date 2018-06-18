package brussels.bric.iot.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import brussels.bric.iot.infrastructure.FileSoundRepository;

/**
 * @author abajramov
 * @since 18/06/2018
 */
@Configuration
@EnableConfigurationProperties(ApplicationProperties.class)
public class ApplicationConfiguration {
    // -------------------------------------------------------------------------------Constant(s)---
    
    // -----------------------------------------------------------------------------Property(ies)---
    @Autowired
    private ApplicationProperties applicationProperties;
    
    // ----------------------------------------------------------------------------Constructor(s)---
    
    // -------------------------------------------------------------------------------Override(s)---
    
    // ---------------------------------------------------------------------------------Method(s)---
    @Bean
    public FileSoundRepository fileSoundRepository() {
        return new FileSoundRepository(applicationProperties);
    }
    
    // -----------------------------------------------------------------------Getter(s)/Setter(s)---
}
