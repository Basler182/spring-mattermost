package de.schk.mattermostspringbootstarter.outgoing;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.schk.mattermostspringbootstarter.shared.Priority;
import jakarta.validation.ValidationException;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;

class OutgoingWebhookPayloadTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void testValidation() {
        OutgoingWebhookPayload payload = new OutgoingWebhookPayload(
                null, null, null, null, null, List.of(), null, null, null
        );

        assertThatExceptionOfType(ValidationException.class)
                .isThrownBy(() -> jakarta.validation.Validation.buildDefaultValidatorFactory().getValidator().validate(payload));
    }

    @Test
    public void testSerialization() throws Exception {
        OutgoingWebhookPayload payload = new OutgoingWebhookPayload(
                "text", "channel", "username", "iconUrl", "iconEmoji", List.of(), "type", Map.of("type", "type"), new Priority(Priority.PriorityLevel.URGENT, true)
        );

        String json = objectMapper.writeValueAsString(payload);
        assertThat(json)
                .contains("text", "channel", "username", "iconUrl",
                        "iconEmoji", "attachments", "type", "type");
        assertThat(json).doesNotContain("valid");
    }
}