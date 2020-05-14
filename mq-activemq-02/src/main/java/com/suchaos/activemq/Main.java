package com.suchaos.activemq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main
 *
 * @author suchao
 * @date 2020/5/11
 */
@SpringBootApplication
@Slf4j
public class Main implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(Main.class);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("start");
    }
}
