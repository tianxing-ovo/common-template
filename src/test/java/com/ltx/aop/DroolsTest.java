package com.ltx.aop;

import com.ltx.drools.entity.Customer;
import com.ltx.drools.entity.Order;
import org.drools.core.base.RuleNameEqualsAgendaFilter;
import org.junit.jupiter.api.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class DroolsTest {

    @Test
    public void test1() {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer container = kieServices.getKieClasspathContainer();
        KieSession kieSession = container.newKieSession();
        //事实对象
        Order order = new Order();
        order.setAmount(1001); //订单金额
        kieSession.insert(order);
        kieSession.fireAllRules(); //执行规则引擎,触发规则
        kieSession.dispose(); //关闭kieSession
        System.out.println("执行规则引擎后,积分为:" + order.getScore());
    }

    @Test
    public void test2() {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer container = kieServices.getKieClasspathContainer();
        KieSession kieSession = container.newKieSession();
        //事实对象
        Order order = new Order();
        Customer customer = new Customer();
        customer.setName("张三");
        List<Order> orderList = new ArrayList<>();
        orderList.add(order);
        customer.setOrderList(orderList);

        //将事实对象加入工作内存中
        kieSession.insert(order);
        kieSession.insert(customer);

        //kieSession.fireAllRules(); //执行规则引擎,触发所有规则
        kieSession.fireAllRules(new RuleNameEqualsAgendaFilter("customer-rule7"));//执行规则引擎,触发指定规则
        kieSession.dispose(); //关闭kieSession
    }

    @Test
    public void test3() {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer container = kieServices.getKieClasspathContainer();
        KieSession kieSession = container.newKieSession();
        kieSession.fireAllRules();//执行规则引擎,触发所有规则
        kieSession.dispose(); //关闭kieSession
    }

    @Test
    public void test4() {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer container = kieServices.getKieClasspathContainer();
        KieSession kieSession = container.newKieSession();
        Customer customer = new Customer();
        customer.setName("李四");
        kieSession.insert(customer);
        kieSession.fireAllRules();//执行规则引擎,触发所有规则
        kieSession.dispose(); //关闭kieSession
    }
}
