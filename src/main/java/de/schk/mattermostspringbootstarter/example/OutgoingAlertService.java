package de.schk.mattermostspringbootstarter.example;

import de.schk.mattermostspringbootstarter.outgoing.MattermostClient;
import de.schk.mattermostspringbootstarter.outgoing.OutgoingWebhookPayload;
import de.schk.mattermostspringbootstarter.shared.Priority;
import org.springframework.stereotype.Service;

@Service
public class OutgoingAlertService {

    private final MattermostClient mattermostClient;

    public OutgoingAlertService(MattermostClient mattermostClient) {
        this.mattermostClient = mattermostClient;
    }

    public void sendAlert(String text) {
        mattermostClient.sendMessage(
                new OutgoingWebhookPayload(text, "alert", "Alert-Bot", null, null, null, null, null, new Priority(Priority.PriorityLevel.URGENT, true)));
    }
}
