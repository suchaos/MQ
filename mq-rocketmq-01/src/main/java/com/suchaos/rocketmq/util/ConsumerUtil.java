package com.suchaos.rocketmq.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.MQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerOrderly;
import org.apache.rocketmq.client.exception.MQClientException;

import java.nio.charset.StandardCharsets;

/**
 * Consumer 工具类
 *
 * @author suchao
 * @date 2020/5/13
 */
@Slf4j
public class ConsumerUtil {

    public static MQPushConsumer getPushConsumer(String namesrvAddr, String consumerGroup, String topic, String subExpression) throws MQClientException {
        DefaultMQPushConsumer pushConsumer = new DefaultMQPushConsumer(consumerGroup);
        pushConsumer.setNamesrvAddr(namesrvAddr);
        pushConsumer.subscribe(topic, subExpression);

        pushConsumer.registerMessageListener((MessageListenerOrderly) (msgs, context) -> {
            log.info("context: " + context);
            msgs.forEach(messageExt -> {
                log.info("msg: " + new String(messageExt.getBody(), StandardCharsets.UTF_8));
            });
            return ConsumeOrderlyStatus.SUCCESS;
        });
        /*pushConsumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                log.info("context: " + context);
                msgs.forEach(messageExt -> {
                    log.info("msg: " + new String(messageExt.getBody(), StandardCharsets.UTF_8));
                });
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });*/
        return pushConsumer;
    }
}
