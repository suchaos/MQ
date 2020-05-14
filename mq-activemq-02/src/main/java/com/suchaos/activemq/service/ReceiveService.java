package com.suchaos.activemq.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

/**
 * ReceiveService
 *
 * @author suchao
 * @date 2020/5/11
 */
@Service
@Slf4j
public class ReceiveService {

    @JmsListener(destination = "spring-boot-queue-1", containerFactory = "jmsListenerContainerTopic")
    public void receiveStringQueue(String msg) {
        log.info("接收到消息...." + msg);
    }
}
