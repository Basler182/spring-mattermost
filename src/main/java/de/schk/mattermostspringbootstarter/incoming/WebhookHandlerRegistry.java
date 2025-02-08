package de.schk.mattermostspringbootstarter.incoming;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

public class WebhookHandlerRegistry {
    private final Map<String, Function<IncomingWebhookRequest, IncomingWebhookResponse>> handlers
            = new ConcurrentHashMap<>();

    public void registerHandler(String hookId, Function<IncomingWebhookRequest, IncomingWebhookResponse> handler) {
        handlers.put(hookId, handler);
    }

    public Optional<IncomingWebhookResponse> handleRequest(String hookId, IncomingWebhookRequest request) {
        return Optional.ofNullable(handlers.get(hookId))
                .map(handler -> handler.apply(request));
    }
}