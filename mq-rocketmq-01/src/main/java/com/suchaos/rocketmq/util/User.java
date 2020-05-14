package com.suchaos.rocketmq.util;

import lombok.Builder;
import lombok.Data;

/**
 * User
 *
 * @author suchao
 * @date 2020/5/14
 */
@Data
@Builder
public class User {
    private String name;
    private Integer age;

    public static User defaultUser() {
        return User.builder().name("苏超").age(18).build();
    }
}
