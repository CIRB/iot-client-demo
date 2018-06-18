package brussels.bric.iot.infrastructure;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import brussels.bric.iot.configuration.ApplicationProperties;
import brussels.bric.iot.domain.model.SoundData;
import brussels.bric.iot.service.SoundRepository;

/**
 * @author abajramov
 * @since 3/26/18
 */
public class FileSoundRepository implements SoundRepository {

    // -------------------------------------------------------------------------------Constant(s)---
    private static final Logger LOGGER = LoggerFactory.getLogger(FileSoundRepository.class);

    // -----------------------------------------------------------------------------Property(ies)---
    private final File measureFile;

    // ----------------------------------------------------------------------------Constructor(s)---
    public FileSoundRepository(ApplicationProperties applicationProperties) {
        this.measureFile = new File(applicationProperties.getMeasureLocation());
        if (!measureFile.exists()) {
            throw new IllegalArgumentException(
                    "Cannot find file located in " + applicationProperties.getMeasureLocation());
        }
    }

    // -------------------------------------------------------------------------------Override(s)---
    @Override
    public SoundData readSound() {
        BufferedReader bufferedReader = null;
        FileReader fileReader = null;
        SoundData soundData = null;
        try {
            fileReader = new FileReader(measureFile);
            bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();
            String[] splittedLine = line.split(",");
            if (splittedLine.length != 4) {
                throw new IllegalArgumentException("File does not follow the csv pattern 'laeq15,laeq60,lceq15,lceq60");
            }
            soundData =
                    new SoundData(LocalDateTime.now().minusYears(1), splittedLine[0], splittedLine[1], splittedLine[2],
                            splittedLine[3]);
        } catch (IOException e) {
            throw new IllegalArgumentException("Cannot read file " + measureFile.toString());
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    LOGGER.error("Cannot close buffered reader", e);
                    throw new IllegalArgumentException("Cannot close buffered reader", e);
                }
            }
            if (fileReader != null) {
                try {
                    fileReader.close();
                } catch (IOException e) {
                    LOGGER.error("Cannot close file reader", e);
                    throw new IllegalArgumentException("Cannot close file reader", e);
                }
            }
        }

        return soundData;
    }

    // ---------------------------------------------------------------------------------Method(s)---

    // -----------------------------------------------------------------------Getter(s)/Setter(s)---
}
