package com.ltx.drools.service;


import com.ltx.drools.entity.Order;
import org.kie.api.KieBase;
import org.kie.api.runtime.KieSession;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author tianxing
 */
@Service
public class RuleService {
    @Resource
    private KieBase kieBase;

    public Order executeRule(Order order) {
        KieSession kieSession = kieBase.newKieSession();
        kieSession.insert(order);
        kieSession.fireAllRules();
        kieSession.dispose();
        return order;
    }
}
