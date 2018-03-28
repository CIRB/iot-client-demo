package brussels.bric.iot.service;

import brussels.bric.iot.domain.model.SoundData; /**
 * @author abajramov
 * @since 3/26/18
 */
public interface IotPlatformGateway {
    // -------------------------------------------------------------------------------Constant(s)---

    // -------------------------------------------------------------------------------Defaults(s)---

    // -------------------------------------------------------------------------------Abstract(s)---
    void sendSoundData(SoundData soundData);
}