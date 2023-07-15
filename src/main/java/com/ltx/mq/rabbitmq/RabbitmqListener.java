package com.ltx.mq.rabbitmq;


import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 监听器
 */
@Component
@RabbitListener(queues = "stock.release.stock.queue")
public class RabbitmqListener {


    /**
     * deliveryTag: 消息的唯一标识符
     * multiple=true -> 确认小于等于当前deliveryTag的所有消息
     * multiple=false -> 确认当前deliveryTag的消息
     */
    @RabbitHandler
    public void handler(Object obj, Message message, Channel channel) throws IOException {
        try {
            System.out.println(obj);
            System.out.println("消息" + message.getMessageProperties().toString());
            //(deliveryTag,multiple)
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);//手动确认
        } catch (Exception e) {
            e.printStackTrace();
            //如果远程调用有异常，消息拒绝，重新返回队列,重新消费
            channel.basicReject(message.getMessageProperties().getDeliveryTag(), true);
        }
    }
}
