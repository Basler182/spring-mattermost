package de.schk.mattermostspringbootstarter.shared;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Priority(
        PriorityLevel priority,
        @JsonProperty("requested_ack")
        boolean requestedAck
) {

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private PriorityLevel priority;
        private boolean requestedAck;

        public Builder priority(PriorityLevel priority) {
            this.priority = priority;
            return this;
        }

        public Builder requestedAck(boolean requestedAck) {
            this.requestedAck = requestedAck;
            return this;
        }

        public Priority build() {
            return new Priority(priority, requestedAck);
        }
    }

    public enum PriorityLevel {
        @JsonProperty("urgent")
        URGENT,
    }
}
