package com.suchaos.activemq.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.jms.Session;
import javax.jms.TextMessage;

/**
 * SenderService
 *
 * @author suchao
 * @date 2020/5/11
 */
@Service
@Slf4j
public class SendService {

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Autowired
    private JmsTemplate jmsTemplate;

    public void send(String destinationName, String message) {
        jmsMessagingTemplate.convertAndSend(destinationName, message);
    }

    public void sendqueue(String destinationName, String message) {
        jmsMessagingTemplate.convertAndSend(new ActiveMQQueue(destinationName), message);
    }

    public void sendWithConfig(String destinationName, String message) {
        jmsTemplate.send(destinationName, session -> {
            log.info(session.toString());
            TextMessage textMessage = session.createTextMessage(message);
            //textMessage.setIntProperty("number", 2);
            return textMessage;
        });
    }
}
