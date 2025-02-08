package de.schk.mattermostspringbootstarter.outgoing;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
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


    @AssertTrue(message = "Either text or attachments must be provided")
    private boolean isValid() {
        return text != null || !attachments.isEmpty();
    }
}
