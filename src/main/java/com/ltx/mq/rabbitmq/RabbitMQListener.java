package com.ltx.mq.rabbitmq;


import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 监听器
 *
 * @author tianxing
 */
@Component
@RabbitListener(queues = "queue")
@Slf4j
public class RabbitMQListener {


    /**
     * deliveryTag: 消息的唯一标识符
     * multiple=true -> 确认小于等于当前deliveryTag的所有消息
     * multiple=false -> 确认当前deliveryTag的消息
     *
     * @param msg     消息内容体
     * @param message 原始消息对象
     * @param channel 通道
     * @throws IOException IO异常
     */
    @RabbitHandler(isDefault = true)
    public void handler(String msg, Message message, Channel channel) throws IOException {
        // 消息的唯一标识符
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        try {
            log.info("接收到消息: {}", msg);
            log.info("{}", message.getMessageProperties().toString());
            // 是否确认多条消息
            boolean multiple = false;
            // 手动确认
            channel.basicAck(deliveryTag, multiple);
        } catch (Exception e) {
            log.error("处理消息时发生异常", e);
            // 是否重新放回队列
            boolean requeue = true;
            // 如果远程调用有异常 -> 消息拒绝 -> 放回队列重新消费
            channel.basicReject(deliveryTag, requeue);
        }
    }
}
