package com.suchaos.rocketmq.util;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;

/**
 * Json Util
 *
 * @author suchao
 * @date 2020/5/14
 */
@Slf4j
public class JsonUtil {
    public static Gson gson = new Gson();

    public static byte[] userToByte(User user) {
        return gson.toJson(user).getBytes(StandardCharsets.UTF_8);
    }

    public static User byteToUser(byte[] bytes) {
        return gson.fromJson(new String(bytes, StandardCharsets.UTF_8), User.class);
    }

    public static void main(String[] args) {
        String json = gson.toJson(User.builder().name("苏超").age(18).build());
        log.info(json);
        byte[] bytes = json.getBytes(StandardCharsets.UTF_8);
        User user = gson.fromJson(new String(bytes, StandardCharsets.UTF_8), User.class);
        log.info("user: " + user);
    }
}
