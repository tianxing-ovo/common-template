package com.ltx.controller;

import com.ltx.mq.rabbitmq.MessageSender;
import io.github.tianxingovo.common.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 消息控制器
 */
@RestController
public class MessageController {

    @Resource
    private MessageSender messageSender;

    @GetMapping("/send")
    public R sendMessage() {
        messageSender.sendMessage("topic.exchange", "queue", "Hello RabbitMQ");
        return R.ok("success");
    }
}
