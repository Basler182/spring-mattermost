package de.schk.mattermostspringbootstarter.configuration;

import org.junit.jupiter.api.Test;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.context.annotation.Configuration;

import static org.assertj.core.api.Assertions.assertThat;

class MattermostPropertiesTest {

    private final ApplicationContextRunner contextRunner = new ApplicationContextRunner()
            .withUserConfiguration(TestConfig.class)
            .withPropertyValues(
                    "mattermost.outgoing.url=http://localhost:8080/mock",
                    "mattermost.incoming.path=/webhooks",
                    "mattermost.incoming.webhooks[0].id=test",
                    "mattermost.incoming.webhooks[0].token=valid"
            );

    @Test
    void testMattermostPropertiesBinding() {
        contextRunner.run(context -> {
            MattermostProperties properties = context.getBean(MattermostProperties.class);
            assertThat(properties.getOutgoing().getUrl()).isEqualTo("http://localhost:8080/mock");
            assertThat(properties.getIncoming().getPath()).isEqualTo("/webhooks");
            assertThat(properties.getIncoming().getWebhooks()).hasSize(1);
            assertThat(properties.getIncoming().getWebhooks().getFirst().getId()).isEqualTo("test");
            assertThat(properties.getIncoming().getWebhooks().getFirst().getToken()).isEqualTo("valid");
        });
    }

    @Configuration
    @EnableConfigurationProperties(MattermostProperties.class)
    static class TestConfig {
    }

}