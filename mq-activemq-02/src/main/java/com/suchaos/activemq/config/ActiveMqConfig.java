package com.suchaos.activemq.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;

import javax.jms.ConnectionFactory;

/**
 * ActiveMqConfig
 *
 * @author suchao
 * @date 2020/5/11
 */
@Configuration
@EnableJms
public class ActiveMqConfig {

    // topic 模式的 ListenerContainer
    @Bean
    public JmsListenerContainerFactory<?> jmsListenerContainerTopic(@Qualifier("jmsConnectionFactory") ConnectionFactory connectionFactory) {
        DefaultJmsListenerContainerFactory bean = new DefaultJmsListenerContainerFactory();
        bean.setPubSubDomain(true);
        bean.setConnectionFactory(connectionFactory);
        return bean;
    }

    // queue 模式的 ListenerContainer
    @Bean
    public JmsListenerContainerFactory<?> jmsListenerContainerQueue(@Qualifier("jmsConnectionFactory") ConnectionFactory connectionFactory) {
        DefaultJmsListenerContainerFactory bean = new DefaultJmsListenerContainerFactory();
        bean.setConnectionFactory(connectionFactory);
        return bean;
    }
}
