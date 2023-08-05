package com.ltx.drools.entity;

import lombok.Data;

import java.util.List;

/**
 * 客户类
 */
@Data
public class Customer {
    private String name;
    private List<Order> orderList; //订单集合
}
