package dev.sergiomarquez.blog.utils.date;

import dev.sergiomarquez.blog.utils.Constants;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class DateUtils {
    public static ZonedDateTime getZonedDateTime() {
        return ZonedDateTime.now(ZoneId.of(Constants.ZONA_HORARIA_ESP));
    }

    public static Instant getInstant() {
        return getZonedDateTime().toInstant();
    }
}
