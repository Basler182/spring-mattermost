package de.schk.mattermostspringbootstarter.outgoing;

import org.springframework.web.client.RestTemplate;

public class MattermostClient {
    private final RestTemplate restTemplate;
    private final String webhookUrl;

    public MattermostClient(RestTemplate restTemplate, String webhookUrl) {
        this.restTemplate = restTemplate;
        this.webhookUrl = webhookUrl;
    }

    public void sendMessage(OutgoingWebhookPayload payload) {
        restTemplate.postForEntity(webhookUrl, payload, String.class);
    }
}