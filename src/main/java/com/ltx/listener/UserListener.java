package com.ltx.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.ltx.entity.po.User;

import java.util.ArrayList;
import java.util.List;

/**
 * User监听器 -> 用于处理读取的Excel数据
 *
 * @author tianxing
 */
public class UserListener extends AnalysisEventListener<User> {

    private final List<User> list = new ArrayList<>();

    /**
     * 一个一个读取 -> 处理读取的数据 -> 可以将数据存储到数据库 -> 进行业务逻辑处理
     *
     * @param user            用户
     * @param analysisContext 分析上下文
     */
    @Override
    public void invoke(User user, AnalysisContext analysisContext) {
        list.add(user);
    }

    /**
     * 数据读取完成后的处理
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }

    /**
     * 获取用户列表
     *
     * @return 用户列表
     */
    public List<User> getUserList() {
        return list;
    }
}
