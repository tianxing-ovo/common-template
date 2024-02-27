package com.ltx.internationalization;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import java.nio.charset.StandardCharsets;
import java.util.Locale;

/**
 * 可重新加载的MessageSource工具类
 */
public class ReloadableResourceBundleMessageSourceUtil {

    private static final ReloadableResourceBundleMessageSource messageSource;

    static {
        messageSource = new ReloadableResourceBundleMessageSource();
        // 可以添加多个分组
        messageSource.setBasenames("i18n/messages");
        messageSource.setDefaultEncoding(StandardCharsets.UTF_8.name());
    }

    /**
     * 使用当前线程的Locale,根据key获取value
     *
     * @param key  key
     * @param args 参数
     */
    public static String getMessage(String key, Object[] args) {
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
    public static String getMessage(String key, Object[] args, Locale locale) {
        return messageSource.getMessage(key, args, locale);
    }
}
