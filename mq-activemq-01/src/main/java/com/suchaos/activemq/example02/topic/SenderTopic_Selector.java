package com.suchaos.activemq.example02.topic;

import com.suchaos.activemq.CONSTANTS;
import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * activemq sender
 * <p>
 * <ol>
 *     <li>获取连接工厂</li>
 *     <li>获取一个 activemq 连接</li>
 *     <li>获取 session</li>
 *     <li>获取 destination，消费者也会从 destination 中取消息</li>
 *     <li>向目的地写入消息</li>
 *     <li>关闭连接</li>
 * </ol>
 *
 * @author suchao
 * @date 2020/5/10
 */
@Slf4j
public class SenderTopic_Selector {

    public static AtomicInteger integer = new AtomicInteger(0);

    public static void main(String[] args) throws JMSException, InterruptedException {
        // 1. 获取连接工厂
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
                ActiveMQConnectionFactory.DEFAULT_USER, ActiveMQConnectionFactory.DEFAULT_PASSWORD,
                CONSTANTS.BROKERURL
        );
        // 2. 获取一个 activemq 连接
        Connection connection = connectionFactory.createConnection();
        connection.start();
        // 3. 获取 session
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        // 4. 获取 destination，消费者也会从 destination 中取消息
        Topic topic = session.createTopic(CONSTANTS.TOPICNAME01);
        // 5. producer 向目的地写入消息
        // 5.1 创建 producer
        MessageProducer producer = session.createProducer(topic);
        for (int i = 0; i < 100; i++) {
            // 5.2 创建消息
            TextMessage textMessage = session.createTextMessage("hi," + i);
            // 设置分组信息
            textMessage.setIntProperty("week", i % 7);
            // 5.3 写入消息
            producer.send(textMessage);

            System.out.println(integer.incrementAndGet() + " send: " + textMessage.getText());

            TimeUnit.SECONDS.sleep(1);
        }
        // 6. 关闭连接
        connection.close();
        log.info("end");
    }
}
