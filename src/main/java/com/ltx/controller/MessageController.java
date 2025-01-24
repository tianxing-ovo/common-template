package com.ltx.controller;

import com.ltx.entity.Result;
import com.ltx.mq.rabbitmq.MessageSender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 消息控制器
 *
 * @author tianxing
 */
@RestController
public class MessageController {

    @Resource
    private MessageSender messageSender;

    /**
     * 发送消息
     *
     * @return 通用响应对象
     */
    @GetMapping("/send")
    public Result sendMessage() {
        messageSender.sendMessage("topic.exchange", "queue", "Hello RabbitMQ");
        return Result.success();
    }
}
