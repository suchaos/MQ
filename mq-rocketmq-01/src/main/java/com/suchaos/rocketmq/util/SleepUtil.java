package com.suchaos.rocketmq.util;

import java.util.concurrent.TimeUnit;

/**
 * SleepUtil
 *
 * @author suchao
 * @date 2020/5/14
 */
public class SleepUtil {

    public static void sleepSecond(int timeout) {
        sleep(timeout, TimeUnit.SECONDS);
    }

    public static void sleep(int timeout, TimeUnit timeUtil) {
        try {
            timeUtil.sleep(timeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
