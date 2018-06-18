package brussels.bric.iot.service;

import brussels.bric.iot.domain.model.SoundData;

/**
 * Defines required methods for {@link SoundService} in order to extract sound level measures.
 *
 * @author abajramov
 * @since 3/26/18
 */
public interface SoundRepository {
    // -------------------------------------------------------------------------------Constant(s)---
    
    // -------------------------------------------------------------------------------Defaults(s)---
    
    // -------------------------------------------------------------------------------Abstract(s)---
    SoundData readSound();
}
