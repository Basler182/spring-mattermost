package de.schk.mattermostspringbootstarter.configuration;

import de.schk.mattermostspringbootstarter.incoming.IncomingWebhookRequest;
import de.schk.mattermostspringbootstarter.incoming.IncomingWebhookResponse;
import de.schk.mattermostspringbootstarter.incoming.WebhookHandlerRegistry;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Configuration;

import static de.schk.mattermostspringbootstarter.incoming.WebhookHandlerRegistryTest.createIncomingWebhookRequest;
import static de.schk.mattermostspringbootstarter.incoming.WebhookHandlerRegistryTest.createIncomingWebhookResponse;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class MattermostHandlerProcessorTest {

    private final WebhookHandlerRegistry registry = new WebhookHandlerRegistry();
    private final MattermostHandlerProcessor processor = new MattermostHandlerProcessor(registry);

    @Test
    public void testHandlerRegistration() {
        TestBean bean = new TestBean();
        processor.postProcessAfterInitialization(bean, "testBean");

        assertThat(registry.handleRequest("test", createRequest()))
                .isPresent();
    }

    private IncomingWebhookRequest createRequest() {
        return createIncomingWebhookRequest("!cmd");
    }

    @Configuration
    static class TestBean {
        @MattermostHandler(hookId = "test", triggerWord = "!cmd")
        public IncomingWebhookResponse handle(IncomingWebhookRequest req) {
            return createIncomingWebhookResponse();
        }
    }
}