package com.ltx;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ltx.entity.User;
import com.ltx.mybatisPlus.SexEnum;
import lombok.SneakyThrows;

public class Main {
    @SneakyThrows
    public static void main(String[] args) {
        User user = new User().setSex(SexEnum.MAN).setAge(18);
        ObjectMapper om = new ObjectMapper();
        System.out.println(om.writeValueAsString(user));
    }
}
