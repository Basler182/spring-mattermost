package de.schk.mattermostspringbootstarter.configuration;

import de.schk.mattermostspringbootstarter.incoming.IncomingWebhookController;
import de.schk.mattermostspringbootstarter.incoming.WebhookHandlerRegistry;
import de.schk.mattermostspringbootstarter.outgoing.MattermostClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(MattermostClient.class)
@EnableConfigurationProperties(MattermostProperties.class)
public class MattermostAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = "mattermost.outgoing", name = "url")
    public MattermostClient mattermostWebhookClient(RestTemplateBuilder restTemplateBuilder,
                                                    MattermostProperties properties) {
        return new MattermostClient(restTemplateBuilder.build(), properties.getOutgoing().getUrl());
    }

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = "mattermost.incoming", name = "webhooks")
    public IncomingWebhookController incomingWebhookController(MattermostProperties properties,
                                                               WebhookHandlerRegistry registry) {
        return new IncomingWebhookController(properties, registry);
    }

    @Bean
    @ConditionalOnMissingBean
    public WebhookHandlerRegistry webhookHandlerRegistry() {
        return new WebhookHandlerRegistry();
    }

    @Bean
    @ConditionalOnMissingBean
    public MattermostHandlerProcessor mattermostHandlerProcessor(WebhookHandlerRegistry registry) {
        return new MattermostHandlerProcessor(registry);
    }
}
