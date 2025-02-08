package de.schk.mattermostspringbootstarter.incoming;

import org.junit.jupiter.api.Test;

import java.util.function.Function;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class WebhookHandlerRegistryTest {

    private final WebhookHandlerRegistry registry = new WebhookHandlerRegistry();

    @Test
    public void testHandlerRegistration() {
        Function<IncomingWebhookRequest, IncomingWebhookResponse> handler = req -> createIncomingWebhookResponse();
        registry.registerHandler("test", handler);

        assertThat(registry.handleRequest("test", createIncomingWebhookRequest()))
                .isPresent();
    }

    @Test
    public void testMissingHandler() {
        assertThat(registry.handleRequest("missing", createIncomingWebhookRequest()))
                .isEmpty();
    }

    public static IncomingWebhookRequest createIncomingWebhookRequest() {
        return  createIncomingWebhookRequest("trigger");
    }

    public static IncomingWebhookRequest createIncomingWebhookRequest(String trigger) {
        return new IncomingWebhookRequest("token", "teamId", "teamDomain", "channelId", "channelName", "userId", "userName", "postId", "text", trigger, null);
    }

    public static IncomingWebhookResponse createIncomingWebhookResponse() {
        return new IncomingWebhookResponse("text", IncomingWebhookResponse.ResponseType.COMMENT, "username", "iconUrl", null, null, null, null);
    }
}