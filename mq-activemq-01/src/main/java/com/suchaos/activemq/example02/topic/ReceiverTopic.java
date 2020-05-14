package com.suchaos.activemq.example02.topic;

import com.suchaos.activemq.CONSTANTS;
import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * activemq receiver
 * <p>
 * <ol>
 *     <li>获取连接工厂</li>
 *     <li>获取一个 activemq 连接</li>
 *     <li>获取 session</li>
 *     <li>获取 destination，消费者也会从 destination 中取消息</li>
 *     <li>向目的地读取消息</li>
 *     <li>关闭连接</li>
 * </ol>
 *
 * @author suchao
 * @date 2020/5/10
 */
@Slf4j
public class ReceiverTopic {

    public static AtomicInteger integer = new AtomicInteger(0);

    public static void main(String[] args) throws JMSException, InterruptedException {
        Connection connection = null;
        try {
            // 1. 获取连接工厂
            ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
                    ActiveMQConnectionFactory.DEFAULT_USER, ActiveMQConnectionFactory.DEFAULT_PASSWORD,
                    CONSTANTS.BROKERURL
            );
            // 2. 获取一个 activemq 连接
            connection = connectionFactory.createConnection();
            connection.start();
            // 3. 获取 session
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            //Session session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);
            // 4. 获取 destination，消费者也会从 destination 中取消息
            Topic topic = session.createTopic(CONSTANTS.TOPICNAME01);
            // 5. 获取消息
            // 5.1 创建 consumer
            MessageConsumer consumer = session.createConsumer(topic);
            while (true) {
                TextMessage receive = (TextMessage) consumer.receive();
                //receive.acknowledge();
                int i = integer.incrementAndGet();
                log.info(i + ", " + receive.getText());
                //TimeUnit.SECONDS.sleep(2);
            }
        } finally {
            // 6. 关闭连接
            connection.close();
            log.info("end");
        }
    }
}
