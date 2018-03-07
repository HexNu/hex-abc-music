package nu.hex.abc.music.service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created 2016-nov-27
 *
 * @author hl
 */
public class Main {

    public static void main(String[] args) {
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now);
        ZoneId eur = ZoneId.of("Europe/Paris");
        ZonedDateTime zdt = ZonedDateTime.of(now, eur);
        System.out.println(zdt);
        ZonedDateTime utc = zdt.withZoneSameInstant(ZoneOffset.UTC);
        System.out.println(utc);
        System.out.println(utc.format(DateTimeFormatter.RFC_1123_DATE_TIME));
        
    }
}
