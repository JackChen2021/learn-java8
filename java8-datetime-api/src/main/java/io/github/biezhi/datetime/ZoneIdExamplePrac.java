package io.github.biezhi.datetime;

import java.time.ZoneId;
import java.util.Set;
import java.util.TimeZone;

/**
 * @author JackChern @create 2022-08-23 16:40
 */
public class ZoneIdExamplePrac {
    public static void main( String[] args ) {
        ZoneId systemDefault = ZoneId.systemDefault();
        System.out.println(systemDefault);

        ZoneId shanghaiZoneId = ZoneId.of("Asia/Shanghai");
        System.out.println(shanghaiZoneId);

        // TimeZone 转换为 ZoneId
        ZoneId zoneId = TimeZone.getDefault().toZoneId();


        Set<String> availableZoneIds = ZoneId.getAvailableZoneIds();
        availableZoneIds.forEach(System.out::println);
    }
}
