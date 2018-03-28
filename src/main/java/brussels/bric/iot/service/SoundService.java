package brussels.bric.iot.service;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import brussels.bric.iot.configuration.ApplicationProperties;
import brussels.bric.iot.domain.model.SoundData;

/**
 * @author abajramov
 * @since 3/26/18
 */
@Component
public class SoundService implements Runnable {
    // -------------------------------------------------------------------------------Constant(s)---

    // -----------------------------------------------------------------------------Property(ies)---
    private final long                     interval;
    private final ScheduledExecutorService scheduledExecutorService;
    private final SoundRepository          soundRepository;
    private final IotPlatformGateway       iotPlatformGateway;

    // ----------------------------------------------------------------------------Constructor(s)---
    @Autowired
    public SoundService(ApplicationProperties applicationProperties, SoundRepository soundRepository,
            IotPlatformGateway iotPlatformGateway) {
        if (applicationProperties.getInterval() == null || applicationProperties.getInterval() <= 0) {
            throw new IllegalArgumentException("Interval must ba a positive value");
        }
        interval = applicationProperties.getInterval();
        this.soundRepository = soundRepository;
        this.iotPlatformGateway = iotPlatformGateway;
        this.scheduledExecutorService = Executors.newScheduledThreadPool(Runtime.getRuntime().availableProcessors());
        this.scheduledExecutorService.scheduleAtFixedRate(this, 0, interval, TimeUnit.SECONDS);
    }

    // -------------------------------------------------------------------------------Override(s)---
    @Override
    public void run() {
        SoundData soundData = soundRepository.readSound();
        iotPlatformGateway.sendSoundData(soundData);
    }

    // ---------------------------------------------------------------------------------Method(s)---

    // -----------------------------------------------------------------------Getter(s)/Setter(s)---
}