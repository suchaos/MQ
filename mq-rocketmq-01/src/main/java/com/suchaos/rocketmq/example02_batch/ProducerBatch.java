package com.suchaos.rocketmq.example02_batch;

import com.suchaos.rocketmq.util.SuConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * RocketMQ Producer -- 批量发送 -- send list -- batch example
 *
 * <ul>
 *  <li>批量消息要求必要具有同一topic、相同消息配置 </li>
 *  <li>不支持延时消息</li>
 *  <li>建议一个批量消息最好不要超过1MB大小</li>
 *  <li>如果不确定是否超过限制，可以手动计算大小分批发送</li>
 * </ul>
 *
 * @author suchao
 * @date 2020/5/13
 * @link http://rocketmq.apache.org/docs/batch-example/
 */
@Slf4j
public class ProducerBatch {

    public static void main(String[] args) throws MQClientException, RemotingException, InterruptedException, MQBrokerException {
        DefaultMQProducer producer = new DefaultMQProducer(SuConstants.PRODUCERGROUP01);
        // 设置 nameserver
        producer.setNamesrvAddr(SuConstants.NAMESERVER);
        producer.start();

        List<Message> messageList = new ArrayList<>();
        // The topic of the messages in one batch should be the same -- 批量消息要求必要具有同一topic、相同消息配置
        /*for (int i = 0; i < 3; i++) {
            if (i == 2) {
                Message message = new Message(SuConstants.TOPIC01, ("hello,rocketmq" + i).getBytes(StandardCharsets.UTF_8));
                messageList.add(message);
            } else {
                Message message = new Message(SuConstants.TOPIC02, ("hello,rocketmq" + i).getBytes(StandardCharsets.UTF_8));
                messageList.add(message);
            }
        }*/
        for (int i = 0; i < 3; i++) {
            Message message = new Message(SuConstants.TOPIC01, ("hello,rocketmq" + i).getBytes(StandardCharsets.UTF_8));
            messageList.add(message);
        }
        // Send message in synchronous mode
        SendResult sendResult = producer.send(messageList);
        log.info(sendResult.toString());
        producer.shutdown();
    }
}
