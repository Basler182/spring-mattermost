package de.schk.mattermostspringbootstarter.incoming;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.ZonedDateTime;

public record IncomingWebhookRequest(
        @JsonProperty("token")
        String token,
        @JsonProperty("team_id")
        String teamId,
        @JsonProperty("team_domain")
        String teamDomain,
        @JsonProperty("channel_id")
        String channelId,
        @JsonProperty("channel_name")
        String channelName,
        @JsonProperty("user_id")
        String userId,
        @JsonProperty("user_name")
        String userName,
        @JsonProperty("post_id")
        String postId,
        @JsonProperty("text")
        String text,
        @JsonProperty("trigger_word")
        String triggerWord,
        @JsonProperty("timestamp")
        ZonedDateTime timestamp
) {
}