package com.guozhi.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 改卷队列消费者
 * @author LiuchangLan
 * @date 2020/11/18 16:47
 */
@Component
@RabbitListener(queues = "${spring.rabbitmq.correctPaper.queue}")
public class CorrectPaperConsumer {

    @RabbitHandler
    public void process(String testMessage) {
        // TODO: 2020/11/18 改卷代码

    }
}
