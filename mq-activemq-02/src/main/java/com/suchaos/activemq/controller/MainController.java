package com.suchaos.activemq.controller;

import com.suchaos.activemq.service.SendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * MainController
 *
 * @author suchao
 * @date 2020/5/11
 */
@RestController
public class MainController {

    @Autowired
    private SendService senderService;

    @RequestMapping("send")
    public String send() {
        senderService.send("spring-boot-queue-1", "hello, activemq");
        return "ok";
    }

    @RequestMapping("send2")
    public String send2() {
        senderService.sendWithConfig("spring-boot-queue-1", "withconfig");
        return "ok";
    }
}
