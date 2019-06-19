package com.buddha.component.common.utils;

import java.nio.charset.StandardCharsets;
import java.util.Locale;

import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Component;

@Component
public class MessageManager {

    public static ReloadableResourceBundleMessageSource getMessageSource() {
        ReloadableResourceBundleMessageSource messageSource =
                new ReloadableResourceBundleMessageSource();
        messageSource.setCacheSeconds(-1);
        messageSource.setDefaultEncoding(StandardCharsets.UTF_8.name());
        messageSource.setBasenames("/i18n/messages");
        return messageSource;
    }

    public static String getMsg(String key, Locale locale) {
        ReloadableResourceBundleMessageSource messageSource = getMessageSource();
        try {
            return messageSource.getMessage(key, null, locale);
        } catch (NoSuchMessageException e) {
            return key;
        }
    }

    public static String getMsg(String key, Locale locale, String... arg) {
        ReloadableResourceBundleMessageSource messageSource = getMessageSource();

        Object[] args = new Object[arg.length];
        for (int i = 0; i < arg.length; i++) {
            args[i] = arg[i];
        }
        try {
            return messageSource.getMessage(key, args, locale);
        } catch (NoSuchMessageException e) {
            return key;
        }
    }

    public static String getMsg(String key) {
        Locale locale = LocaleContextHolder.getLocale();
        return getMsg(key, locale);
    }

    public static String getMsg(String key, String... arg) {
        Locale locale = LocaleContextHolder.getLocale();
        return getMsg(key, locale, arg);
    }
}
