package de.schk.mattermostspringbootstarter.incoming;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import de.schk.mattermostspringbootstarter.outgoing.Attachment;
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

    @AssertTrue(message = "Either text or attachments must be provided")
    public boolean isValid() {
        return text != null || attachments != null;
    }

    public record Priority(
            PriorityLevel priority,
            @JsonProperty("requested_ack")
            boolean requestedAck
    ) {

        public enum PriorityLevel {
            @JsonProperty("urgent")
            URGENT,
        }
    }

    public enum ResponseType {
        @JsonProperty("comment")
        COMMENT,
        @JsonProperty("post")
        POST
    }
}