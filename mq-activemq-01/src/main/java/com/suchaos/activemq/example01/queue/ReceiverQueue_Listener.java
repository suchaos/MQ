package com.suchaos.activemq.example01.queue;

import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.IOException;
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
public class ReceiverQueue_Listener {

    public static String BROKERURL = "tcp://localhost:61616";

    public static AtomicInteger integer = new AtomicInteger(0);

    public static void main(String[] args) throws JMSException, InterruptedException {
        Connection connection = null;
        try {
            // 1. 获取连接工厂
            ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
                    ActiveMQConnectionFactory.DEFAULT_USER, ActiveMQConnectionFactory.DEFAULT_PASSWORD,
                    BROKERURL
            );
            // 2. 获取一个 activemq 连接
            connection = connectionFactory.createConnection();
            connection.start();
            // 3. 获取 session
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            //Session session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);
            // 4. 获取 destination，消费者也会从 destination 中取消息
            Queue queue = session.createQueue("destination-queue-a");
            // 5. 获取消息
            // 5.1 创建 consumer
            MessageConsumer consumer = session.createConsumer(queue);

            consumer.setMessageListener(message -> {
                TextMessage receive = (TextMessage) message;
                //receive.acknowledge();
                int i = integer.incrementAndGet();
                try {
                    log.info(i + ", " + receive.getText());
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            });
            //System.in.read();
        } finally {
            // 6. 关闭连接
            //connection.close();
            log.info("end");
        }
    }
}
