# Mattermost Spring Boot Starter

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

### Annotation Parameters

`@MattermostHandler` supports:

| Parameter     | Description                              |
|---------------|------------------------------------------|
| `hookId`      | Webhook configuration ID from properties |
| `triggerWord` | Match specific trigger words             |
| `channelId`   | Filter by channel ID                     |
| `textPattern` | Regex pattern to match message text      |