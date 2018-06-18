package brussels.bric.iot.service;

import brussels.bric.iot.domain.model.SoundData;

/**
 * Defines required methods for {@link SoundService} in order to exchange information with Brussels' IoT platform.
 *
 * @author abajramov
 * @since 3/26/18
 */
public interface IotPlatformGateway {
    // -------------------------------------------------------------------------------Constant(s)---

    // -------------------------------------------------------------------------------Defaults(s)---

    // -------------------------------------------------------------------------------Abstract(s)---
    void sendSoundData(SoundData soundData);
}
