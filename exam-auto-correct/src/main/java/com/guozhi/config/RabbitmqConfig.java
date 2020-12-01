package com.guozhi.config;

import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * RabbitMq 设置多线程消费者
 * @author LiuchangLan
 * @date 2020/11/19 10:01
 */
@Configuration
public class RabbitmqConfig {

    @Bean("customContainerFactory")
    public SimpleRabbitListenerContainerFactory containerFactory(SimpleRabbitListenerContainerFactoryConfigurer configurer, ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConcurrentConsumers(20);  //设置线程数
        factory.setMaxConcurrentConsumers(20); //最大线程数
        configurer.configure(factory, connectionFactory);
        return factory;
    }

}
