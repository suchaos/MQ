package com.suchaos.rocketmq.example01_simple;

import com.suchaos.rocketmq.util.SuConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.nio.charset.StandardCharsets;

/**
 * RocketMQ Producer
 *
 * @author suchao
 * @date 2020/5/13
 */
@Slf4j
public class Producer {

    public static void main(String[] args) throws MQClientException, RemotingException, InterruptedException, MQBrokerException {
        DefaultMQProducer producer = new DefaultMQProducer(SuConstants.PRODUCERGROUP01);
        // 设置 nameserver
        producer.setNamesrvAddr(SuConstants.NAMESERVER);
        producer.start();
        Message message = new Message(SuConstants.TOPIC01, "hello,rocketmq3".getBytes(StandardCharsets.UTF_8));
        // Send message in synchronous mode
        SendResult sendResult = producer.send(message);
        log.info(sendResult.toString());
        producer.shutdown();
    }
}
