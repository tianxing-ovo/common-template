package com.ltx.internationalization;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Locale;

/**
 * MessageSource工具类
 */
@Component
public class MessageSourceUtil {

    @Resource
    private MessageSource messageSource;

    /**
     * 使用当前线程的Locale,根据key获取value
     *
     * @param key  key
     * @param args 参数
     */
    public String getMessage(String key, Object[] args) {
        Locale locale = LocaleContextHolder.getLocale();
        return getMessage(key, args, locale);
    }

    /**
     * 根据key获取value
     *
     * @param key    key
     * @param locale 区域
     * @param args   参数
     * @return value
     */
    public String getMessage(String key, Object[] args, Locale locale) {
        return messageSource.getMessage(key, args, locale);
    }
}
