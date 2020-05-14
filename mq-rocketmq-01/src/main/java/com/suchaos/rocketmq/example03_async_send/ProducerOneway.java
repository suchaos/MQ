package com.suchaos.rocketmq.example03_async_send;

import com.suchaos.rocketmq.util.SuConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.nio.charset.StandardCharsets;

/**
 * RocketMQ Producer -- 异步发送消息，oneway -- 单向消息
 *
 * 这种效率最高
 *
 * @author suchao
 * @date 2020/5/13
 * @link http://rocketmq.apache.org/docs/batch-example/
 */
@Slf4j
public class ProducerOneway {

    public static void main(String[] args) throws MQClientException, RemotingException, InterruptedException, MQBrokerException {
        DefaultMQProducer producer = new DefaultMQProducer(SuConstants.PRODUCERGROUP01);
        // 设置 nameserver
        producer.setNamesrvAddr(SuConstants.NAMESERVER);
        producer.start();

        Message message = new Message(SuConstants.TOPIC01, ("hello,rocketmq,send oneway").getBytes(StandardCharsets.UTF_8));

        // Send message to broker asynchronously.
        // producer.setRetryTimesWhenSendAsyncFailed();
        producer.sendOneway(message);
        //producer.shutdown();
    }
}
