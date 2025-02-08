package de.schk.mattermostspringbootstarter.incoming;

import de.schk.mattermostspringbootstarter.configuration.MattermostProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("${mattermost.incoming.path}")
public class IncomingWebhookController {
    private final WebhookHandlerRegistry handlerRegistry;
    private final Map<String, String> tokenToHookId;

    public IncomingWebhookController(MattermostProperties properties, WebhookHandlerRegistry registry) {
        this.handlerRegistry = registry;
        this.tokenToHookId = properties.getIncoming().getWebhooks().stream()
                .collect(Collectors.toMap(
                        MattermostProperties.Incoming.WebhookConfig::getToken,
                        MattermostProperties.Incoming.WebhookConfig::getId
                ));
    }

    @PostMapping
    public ResponseEntity<IncomingWebhookResponse> handleWebhook(@RequestBody IncomingWebhookRequest request) {
        String hookId = tokenToHookId.get(request.token());
        if (hookId == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        return handlerRegistry.handleRequest(hookId, request)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
