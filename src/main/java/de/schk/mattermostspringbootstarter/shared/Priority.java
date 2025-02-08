package de.schk.mattermostspringbootstarter.shared;

import com.fasterxml.jackson.annotation.JsonProperty;

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
