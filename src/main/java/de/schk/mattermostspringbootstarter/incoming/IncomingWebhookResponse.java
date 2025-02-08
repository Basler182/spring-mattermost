package de.schk.mattermostspringbootstarter.incoming;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import de.schk.mattermostspringbootstarter.shared.Attachment;
import de.schk.mattermostspringbootstarter.shared.Priority;
import jakarta.validation.constraints.AssertTrue;

import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record IncomingWebhookResponse(
        String text,
        @JsonProperty("response_type")
        ResponseType responseType,
        String username,
        @JsonProperty("icon_url")
        String iconUrl,
        List<Attachment> attachments,
        String type,
        Map<String, Object> props,
        Priority priority
) {

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String text;
        private ResponseType responseType;
        private String username;
        private String iconUrl;
        private List<Attachment> attachments;
        private String type;
        private Map<String, Object> props;
        private Priority priority;

        public Builder text(String text) {
            this.text = text;
            return this;
        }

        public Builder responseType(ResponseType responseType) {
            this.responseType = responseType;
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

        public IncomingWebhookResponse build() {
            return new IncomingWebhookResponse(text, responseType, username, iconUrl, attachments, type, props, priority);
        }
    }

    @JsonIgnore
    @AssertTrue(message = "Either text or attachments must be provided")
    public boolean isValid() {
        return text != null || attachments != null;
    }

    public enum ResponseType {
        @JsonProperty("comment")
        COMMENT,
        @JsonProperty("post")
        POST
    }
}