package de.schk.mattermostspringbootstarter.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

@ConfigurationProperties(prefix = "mattermost")
public class MattermostProperties {

    private Outgoing outgoing;
    private Incoming incoming;

    public static class Outgoing {
        private String url;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        @Override
        public String toString() {
            return "Outgoing{" +
                    "url='" + url + '\'' +
                    '}';
        }
    }

    public static class Incoming {
        private String path;
        private List<WebhookConfig> webhooks = new ArrayList<>();

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public List<WebhookConfig> getWebhooks() {
            return webhooks;
        }

        public void setWebhooks(List<WebhookConfig> webhooks) {
            this.webhooks = webhooks;
        }

        public static class WebhookConfig {
            private String id;
            private String token;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getToken() {
                return token;
            }

            public void setToken(String token) {
                this.token = token;
            }

            @Override
            public String toString() {
                return "WebhookConfig{" +
                        "id='" + id + '\'' +
                        ", token='" + token + '\'' +
                        '}';
            }
        }

        @Override
        public String toString() {
            return "Incoming{" +
                    "path='" + path + '\'' +
                    ", webhooks=" + webhooks +
                    '}';
        }
    }

    public Outgoing getOutgoing() {
        return outgoing;
    }

    public void setOutgoing(Outgoing outgoing) {
        this.outgoing = outgoing;
    }

    public Incoming getIncoming() {
        return incoming;
    }

    public void setIncoming(Incoming incoming) {
        this.incoming = incoming;
    }

    @Override
    public String toString() {
        return "MattermostProperties{" +
                "outgoing=" + outgoing +
                ", incoming=" + incoming +
                '}';
    }
}
