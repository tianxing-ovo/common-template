package com.ltx.drools.controller;

import com.ltx.drools.entity.Order;
import com.ltx.drools.service.RuleService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class OrderController {

    @Resource
    RuleService ruleService;

    @PostMapping("/orders")
    public Order saveOrder(@RequestBody Order order) {
        return ruleService.executeRule(order);
    }
}


