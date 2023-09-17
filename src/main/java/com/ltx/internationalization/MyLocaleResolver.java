package com.ltx.internationalization;

import org.apache.commons.lang3.StringUtils;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * 自定义LocaleResolver
 */
public class MyLocaleResolver implements LocaleResolver {

    @Override
    @NonNull
    public Locale resolveLocale(HttpServletRequest request) {
        String lang = request.getParameter("lang");
        if (StringUtils.isNotBlank(lang)) {
            String[] arr = lang.split("-");
            return new Locale(arr[0], arr[1]);
        }
        return Locale.getDefault();
    }

    @Override
    public void setLocale(@NonNull HttpServletRequest request, HttpServletResponse response, Locale locale) {

    }
}
