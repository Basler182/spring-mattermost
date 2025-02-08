package de.schk.mattermostspringbootstarter.outgoing;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import de.schk.mattermostspringbootstarter.shared.Attachment;
import de.schk.mattermostspringbootstarter.shared.Priority;
import jakarta.validation.constraints.AssertTrue;

import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record OutgoingWebhookPayload(
        String text,
        String channel,
        String username,
        @JsonProperty("icon_url")
        String iconUrl,
        @JsonProperty("icon_emoji")
        String iconEmoji,
        List<Attachment> attachments,
        String type,
        Map<String, Object> props,
        Priority priority) {

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String text;
        private String channel;
        private String username;
        private String iconUrl;
        private String iconEmoji;
        private List<Attachment> attachments;
        private String type;
        private Map<String, Object> props;
        private Priority priority;

        public Builder text(String text) {
            this.text = text;
            return this;
        }

        public Builder channel(String channel) {
            this.channel = channel;
            return this;
        }

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public Builder iconUrl(String iconUrl) {
            this.iconUrl = iconUrl;
            return this;
        }

        public Builder iconEmoji(String iconEmoji) {
            this.iconEmoji = iconEmoji;
            return this;
        }

        public Builder attachments(List<Attachment> attachments) {
            this.attachments = attachments;
            return this;
        }

        public Builder type(String type) {
            this.type = type;
            return this;
        }

        public Builder props(Map<String, Object> props) {
            this.props = props;
            return this;
        }

        public Builder priority(Priority priority) {
            this.priority = priority;
            return this;
        }

        public OutgoingWebhookPayload build() {
            return new OutgoingWebhookPayload(text, channel, username, iconUrl, iconEmoji, attachments, type, props, priority);
        }
    }

    @AssertTrue(message = "Either text or attachments must be provided")
    private boolean isValid() {
        return text != null || !attachments.isEmpty();
    }
}
