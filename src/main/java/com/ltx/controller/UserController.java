package com.ltx.controller;

import com.ltx.dao.UserDao;
import com.ltx.entity.request.UserRequestBody;
import common.R;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Locale;

@RestController
public class UserController {

    @Resource
    UserDao userDao;
    @Resource
    MessageSource messageSource;

    @PostMapping("/users")
    public R query(@RequestBody UserRequestBody requestBody) {
        Locale locale = LocaleContextHolder.getLocale();
        String message = messageSource.getMessage("greeting", new Object[]{"jack"}, locale);
        return R.ok(message).put("userList", userDao.query(requestBody));
    }
}
