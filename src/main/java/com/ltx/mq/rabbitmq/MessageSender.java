package com.ltx.mq.rabbitmq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 消息发送者
 */
@Component
public class MessageSender {


    @Resource
    private RabbitTemplate rabbitTemplate;


    /**
     * 发送消息
     *
     * @param exchangeName 交换机名称
     * @param routingKey   路由键
     * @param message      消息
     */
    public void sendMessage(String exchangeName, String routingKey, String message) {
        rabbitTemplate.convertAndSend(exchangeName, routingKey, message);
    }
}

