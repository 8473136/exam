package com.guozhi.rabbitmq;


import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 默认交换器
 * @author LiuchangLan
 * @date 2020/11/18 16:20
 */
@Configuration
@Slf4j
public class DirectConfig {

    @Value("${spring.rabbitmq.correctPaper.queue}")
    private String queueName;
    @Value("${spring.rabbitmq.correctPaper.exchange}")
    private String exchangeName;
    @Value("${spring.rabbitmq.correctPaper.routingKey}")
    private String routingKey;

    /**
     * @description 创建改卷队列
     * @author LiuChangLan
     * @since 2020/11/18 16:37
     */
    @Bean
    Queue correctPaperQueue(){
        // durable:是否持久化,默认是false,持久化队列：会被存储在磁盘上，当消息代理重启时仍然存在，暂存队列：当前连接有效
        // exclusive:默认也是false，只能被当前创建的连接使用，而且当连接关闭后队列即被删除。此参考优先级高于durable
        // autoDelete:是否自动删除，当没有生产者或者消费者使用此队列，该队列会自动删除。
       return new Queue(queueName,true,false,false);
    }

    /**
     * @description 创建改卷交换机
     * @author LiuChangLan
     * @since 2020/11/18 16:39
     */
    @Bean
    DirectExchange correctPaperExchange(){
        return new DirectExchange(exchangeName,true,false);
    }

    /**
     * @description 绑定交换机和队列 并且设置关键字
     * @author LiuChangLan
     * @since 2020/11/18 16:40
     */
    @Bean
    Binding bindingDirect() {
        return BindingBuilder.bind(correctPaperQueue()).to(correctPaperExchange()).with(routingKey);
    }
}
