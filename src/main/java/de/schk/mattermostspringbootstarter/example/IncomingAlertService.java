package de.schk.mattermostspringbootstarter.example;

import de.schk.mattermostspringbootstarter.shared.Priority;
import de.schk.mattermostspringbootstarter.configuration.MattermostHandler;
import de.schk.mattermostspringbootstarter.incoming.IncomingWebhookRequest;
import de.schk.mattermostspringbootstarter.incoming.IncomingWebhookResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IncomingAlertService {
    @MattermostHandler(
            hookId = "alerts",
            triggerWord = "!alert",
            textPattern = ".*CRITICAL.*"
    )
    public IncomingWebhookResponse handleCriticalAlert(IncomingWebhookRequest request) {
        return new IncomingWebhookResponse(
                "text",
                IncomingWebhookResponse.ResponseType.COMMENT,
                "username",
                "iconUrl",
                List.of(),
                "type",
                null,
                new Priority(Priority.PriorityLevel.URGENT, true));

    }
}
