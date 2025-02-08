package de.schk.mattermostspringbootstarter.configuration;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface MattermostHandler {
    String hookId();
    String triggerWord() default "";
    String channelId() default "";
    String textPattern() default "";
}