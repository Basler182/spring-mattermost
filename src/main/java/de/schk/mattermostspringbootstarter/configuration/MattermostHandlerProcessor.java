package de.schk.mattermostspringbootstarter.configuration;

import de.schk.mattermostspringbootstarter.incoming.IncomingWebhookRequest;
import de.schk.mattermostspringbootstarter.incoming.IncomingWebhookResponse;
import de.schk.mattermostspringbootstarter.incoming.WebhookHandlerRegistry;
import jakarta.annotation.Nonnull;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import java.lang.reflect.Method;
import java.util.function.Predicate;

public class MattermostHandlerProcessor implements BeanPostProcessor {
    private final WebhookHandlerRegistry registry;

    public MattermostHandlerProcessor(WebhookHandlerRegistry registry) {
        this.registry = registry;
    }

    @Override
    public @Nonnull Object postProcessAfterInitialization( Object bean, @Nonnull String beanName) throws BeansException {
        for (Method method : bean.getClass().getDeclaredMethods()) {
            if (method.isAnnotationPresent(MattermostHandler.class)) {
                processHandlerMethod(bean, method);
            }
        }
        return bean;
    }

    private void processHandlerMethod(Object bean, Method method) {
        MattermostHandler annotation = method.getAnnotation(MattermostHandler.class);

        Predicate<IncomingWebhookRequest> predicate = request ->
                (annotation.triggerWord().isEmpty() || annotation.triggerWord().equals(request.triggerWord())) &&
                        (annotation.channelId().isEmpty() || annotation.channelId().equals(request.channelId())) &&
                        (annotation.textPattern().isEmpty() || request.text().matches(annotation.textPattern()));

        registry.registerHandler(annotation.hookId(), request -> {
            try {
                if (predicate.test(request)) {
                    return (IncomingWebhookResponse) method.invoke(bean, request);
                }
                return null;
            } catch (Exception e) {
                throw new RuntimeException("Handler invocation failed", e);
            }
        });
    }
}
