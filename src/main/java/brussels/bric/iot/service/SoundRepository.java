package brussels.bric.iot.service;

import java.io.FileNotFoundException;
import brussels.bric.iot.domain.model.SoundData;

/**
 * @author abajramov
 * @since 3/26/18
 */
public interface SoundRepository {
    // -------------------------------------------------------------------------------Constant(s)---

    // -------------------------------------------------------------------------------Defaults(s)---

    // -------------------------------------------------------------------------------Abstract(s)---
    SoundData readSound();
}