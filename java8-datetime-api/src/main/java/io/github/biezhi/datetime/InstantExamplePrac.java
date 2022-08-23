package io.github.biezhi.datetime;

import java.time.Instant;

/**
 * Instant 示例
 *
 * @author biezhi
 * @date 2018/3/2
 */
public class InstantExamplePrac {

    public static void main(String[] args) {

        // 创建一个Instant实例
        Instant now = Instant.now();

        int nano = now.getNano();
        long epochSecond = now.getEpochSecond();

        // 3秒后
        Instant later = now.plusSeconds(3);
        // 3秒前
        Instant earlier = now.minusSeconds(3);

        System.out.println("current : " + now.toString());
        System.out.println("later   : " + later);
        System.out.println("earlier : " + earlier);
    }
}
