package brussels.bric.iot.domain.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import lombok.Getter;

/**
 * @author abajramov
 * @since 3/26/18
 */
@Getter
public class SoundData {
    // -------------------------------------------------------------------------------Constant(s)---

    // -----------------------------------------------------------------------------Property(ies)---
    private final String        laeq15;
    private final String        laeq60;
    private final String        lceq15;
    private final String        lceq60;
    private final LocalDateTime redOn;

    // ----------------------------------------------------------------------------Constructor(s)---
    public SoundData(LocalDateTime redOn, String laeq15, String laeq60, String lceq15, String lceq60) {
        this.laeq15 = laeq15;
        this.laeq60 = laeq60;
        this.lceq15 = lceq15;
        this.lceq60 = lceq60;
        this.redOn = redOn;
    }

    public String redOnIso8601() {
        return redOn.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }

    // -------------------------------------------------------------------------------Override(s)---

    // ---------------------------------------------------------------------------------Method(s)---

    // -----------------------------------------------------------------------Getter(s)/Setter(s)---
}