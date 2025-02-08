package de.schk.mattermostspringbootstarter.configuration;

import de.schk.mattermostspringbootstarter.incoming.IncomingWebhookController;
import de.schk.mattermostspringbootstarter.outgoing.MattermostClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@SpringBootTest(classes = MattermostAutoConfigurationTest.TestConfig.class)
@EnableConfigurationProperties(MattermostProperties.class)
@TestPropertySource(properties = {
        "mattermost.outgoing.url=https://example.com",
        "mattermost.incoming.webhooks[0].id=test",
        "mattermost.incoming.webhooks[0].token=secret"
})
public class MattermostAutoConfigurationTest {

    @TestConfiguration
    @Import(MattermostAutoConfiguration.class)
    static class TestConfig {
        @Bean
        public RestTemplateBuilder restTemplateBuilder() {
            return new RestTemplateBuilder();
        }
    }

    @Autowired(required = false)
    private MattermostClient webhookClient;

    @Autowired(required = false)
    private IncomingWebhookController controller;

    @Test
    public void testAutoConfigurationWithProperties() {
        assertThat(webhookClient).isNotNull();
        assertThat(controller).isNotNull();
    }
}