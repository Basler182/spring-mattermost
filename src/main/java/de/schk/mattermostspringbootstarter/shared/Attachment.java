package de.schk.mattermostspringbootstarter.shared;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record Attachment(
        String fallback,
        String color,
        String pretext,
        String text,
        @JsonProperty("author_name")
        String authorName,
        @JsonProperty("author_icon")
        String authorIcon,
        @JsonProperty("author_link")
        String authorLink,
        String title,
        @JsonProperty("title_link")
        String titleLink,
        List<Field> fields,
        String imageUrl
) {

    record Field(
            @JsonProperty("short")
            boolean shortField,
            String title,
            String value
    ) {
    }
}
