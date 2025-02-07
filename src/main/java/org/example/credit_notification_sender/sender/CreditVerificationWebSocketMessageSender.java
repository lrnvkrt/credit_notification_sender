package org.example.credit_notification_sender.sender;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class WebSocketMessageSender {
    private final SimpMessagingTemplate messagingTemplate;

    public WebSocketMessageSender(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }
    public void sendMessage(String message) {
        messagingTemplate.convertAndSend("/topic/credit_notification_sender", message);
    }
}
