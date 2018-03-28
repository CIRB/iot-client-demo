package brussels.bric.iot;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * The application launcher test.
 *
 * @author Adi Bajramov
 *
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

}
