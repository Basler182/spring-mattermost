package de.schk.mattermostspringbootstarter.shared;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
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

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String fallback;
        private String color;
        private String pretext;
        private String text;
        private String authorName;
        private String authorIcon;
        private String authorLink;
        private String title;
        private String titleLink;
        private List<Field> fields = new ArrayList<>();
        private String imageUrl;

        public Builder fallback(String fallback) {
            this.fallback = fallback;
            return this;
        }

        public Builder color(String color) {
            this.color = color;
            return this;
        }

        public Builder pretext(String pretext) {
            this.pretext = pretext;
            return this;
        }

        public Builder text(String text) {
            this.text = text;
            return this;
        }

        public Builder authorName(String authorName) {
            this.authorName = authorName;
            return this;
        }

        public Builder authorIcon(String authorIcon) {
            this.authorIcon = authorIcon;
            return this;
        }

        public Builder authorLink(String authorLink) {
            this.authorLink = authorLink;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder titleLink(String titleLink) {
            this.titleLink = titleLink;
            return this;
        }

        public Builder addField(Field field) {
            this.fields.add(field);
            return this;
        }

        public Builder imageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
            return this;
        }

        public Attachment build() {
            return new Attachment(fallback, color, pretext, text, authorName, authorIcon, authorLink, title, titleLink, fields, imageUrl);
        }
    }

    public record Field(
            @JsonProperty("short")
            boolean shortField,
            String title,
            String value
    ) {
        public static FieldBuilder builder() {
            return new FieldBuilder();
        }

        public static class FieldBuilder {
            private boolean shortField;
            private String title;
            private String value;

            public FieldBuilder shortField(boolean shortField) {
                this.shortField = shortField;
                return this;
            }

            public FieldBuilder title(String title) {
                this.title = title;
                return this;
            }

            public FieldBuilder value(String value) {
                this.value = value;
                return this;
            }

            public Field build() {
                return new Field(shortField, title, value);
            }
        }
    }
}
