package com.suchaos.rocketmq.example02_batch;

import com.suchaos.rocketmq.util.ConsumerUtil;
import com.suchaos.rocketmq.util.SuConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.MQPushConsumer;
import org.apache.rocketmq.client.exception.MQClientException;

/**
 * RocketMQ Consumer
 *
 * @author suchao
 * @date 2020/5/13
 */
@Slf4j
public class Consumer {

    public static void main(String[] args) throws MQClientException {
        MQPushConsumer pushConsumer = ConsumerUtil.getPushConsumer(SuConstants.NAMESERVER,
                SuConstants.CONSUMERGROUP02, SuConstants.TOPIC01, "*");
        pushConsumer.start();
        log.info("consumer start...");
    }
}
