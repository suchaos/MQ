package com.suchaos.rocketmq.example03_async_send;

import com.suchaos.rocketmq.util.SuConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.nio.charset.StandardCharsets;

/**
 * RocketMQ Producer -- 异步发送消息，设置回调
 *
 * @author suchao
 * @date 2020/5/13
 * @link http://rocketmq.apache.org/docs/batch-example/
 */
@Slf4j
public class ProducerCallback {

    public static void main(String[] args) throws MQClientException, RemotingException, InterruptedException, MQBrokerException {
        DefaultMQProducer producer = new DefaultMQProducer(SuConstants.PRODUCERGROUP01);
        // 设置 nameserver
        producer.setNamesrvAddr(SuConstants.NAMESERVER);
        producer.start();

        Message message = new Message(SuConstants.TOPIC01, ("hello,rocketmq,async message").getBytes(StandardCharsets.UTF_8));

        // Send message to broker asynchronously.
        // producer.setRetryTimesWhenSendAsyncFailed();
        producer.send(message, new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                log.info("send success: " + sendResult);
            }

            @Override
            public void onException(Throwable e) {
                log.error("send fail", e);
                // 可以 switch 不同异常， 尝试重投
                // 或者调整业务逻辑
            }
        });
        // producer.shutdown();
    }
}
