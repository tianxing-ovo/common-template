package com.ltx.drools.entity;

import lombok.Data;

/**
 * 订单类
 *
 * @author tianxing
 */
@Data
public class Order {
    // 金额
    private Integer amount;
    // 积分
    private Integer score;
}
