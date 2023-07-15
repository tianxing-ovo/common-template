package com.ltx.mq.rabbitmq;

import lombok.AllArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Component;

/**
 * 消息发送者
 */
@Component
@AllArgsConstructor
public class MessageSender {

    private AmqpTemplate template;


    public void sendMessage(String queueName, Object message) {
        template.convertAndSend("Exchange", "RoutingKey", "Hello, RabbitMQ!");
    }
}

