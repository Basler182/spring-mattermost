# Mattermost Spring Boot Starter [![Release](https://jitpack.io/v/Basler182/spring-mattermost.svg)](https://jitpack.io/#Basler182/spring-mattermost) ![Weekly download statistics](https://jitpack.io/v/Basler182/spring-mattermost/week.svg) ![Monthly download statistics](https://jitpack.io/v/Basler182/spring-mattermost/month.svg)

A Spring Boot Starter for seamless integration with Mattermost webhooks, supporting both incoming and outgoing webhook
handling with full parameter support.

## Features

- **Bidirectional Webhook Handling**
    - Send messages to Mattermost (outgoing webhooks)
    - Receive and process messages from Mattermost (incoming webhooks)
- **Annotation-based Handler Registration**
- **Full Parameter Support** for all Mattermost webhook types
- **Autoconfiguration** with Spring Boot
- **Validation** for required parameters
- **Security** with token validation
- **Type-safe Payload Building** with fluent API

## Configuration

`application.yml` example:

```yaml
mattermost:
  outgoing:
    url: https://your-mattermost-server/hooks/your-incoming-webhook-id
  incoming:
    path: /webhooks
    webhooks:
      - id: alerts
        token: your-outgoing-webhook-token
      - id: ci
        token: your-ci-webhook-token
```

## Usage

### Sending Messages to Mattermost

```java

@Service
public class AlertService {
    private final MattermostWebhookClient webhookClient;

    public void sendAlert() {
        webhookClient.sendMessage(new OutgoingWebhookPayload());
    }
}
```

### Receiving Webhooks from Mattermost

```java

@Service
public class AlertHandler {
    @MattermostHandler(
            hookId = "alerts",
            triggerWord = "!alert",
            textPattern = ".*CRITICAL.*"
    )
    public IncomingWebhookResponse handleCriticalAlert(IncomingWebhookRequest request) {
        // do anything here
        return new IncomingWebhookResponse();
    }
}
```

### Example Usage of `IncomingWebhookResponse` Fluent API

```java
IncomingWebhookResponse response = IncomingWebhookResponse.builder()
        .text("This is a test message")
        .responseType(IncomingWebhookResponse.ResponseType.COMMENT)
        .username("test-user")
        .iconUrl("https://example.com/icon.png")
        .attachments(List.of(Attachment.builder().title("title").build()))
        .type("example-type")
        .props(Map.of("key1", "value1", "key2", "value2"))
        .priority(Priority.builder().priority(Priority.PriorityLevel.URGENT).build())
        .build();
```

### Example Usage of `OutgoingWebhookPayload` Fluent API

```java
OutgoingWebhookPayload payload = OutgoingWebhookPayload.builder()
        .text("This is a test message")
        .channel("test-channel")
        .username("test-user")
        .iconUrl("https://example.com/icon.png")
        .iconEmoji(":smile:")
        .attachments(List.of(Attachment.builder().text("Attachment text").build()))
        .type("example-type")
        .props(Map.of("key1", "value1", "key2", "value2"))
        .priority(Priority.builder().priority(Priority.PriorityLevel.URGENT).build())
        .build();
```

### Annotation Parameters

`@MattermostHandler` supports:

| Parameter     | Description                              |
|---------------|------------------------------------------|
| `hookId`      | Webhook configuration ID from properties |
| `triggerWord` | Match specific trigger words             |
| `channelId`   | Filter by channel ID                     |
| `textPattern` | Regex pattern to match message text      |