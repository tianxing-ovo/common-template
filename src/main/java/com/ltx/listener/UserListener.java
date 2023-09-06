package com.ltx.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.ltx.entity.User;

import java.util.ArrayList;
import java.util.List;

/**
 * User监听器,用于处理读取的Excel数据
 */
public class UserListener extends AnalysisEventListener<User> {

    List<User> list = new ArrayList<>();

    /**
     * 处理读取的数据,可以将数据存储到数据库,进行业务逻辑处理
     * 一个一个读取
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

    public List<User> getUserList() {
        return list;
    }
}
