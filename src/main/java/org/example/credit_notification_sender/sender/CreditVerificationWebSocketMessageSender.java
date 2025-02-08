package org.example.credit_notification_sender.sender;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import com.example.sop_contracts.messages.CreditCheckVerificationMessage;
import org.springframework.stereotype.Component;

@Component
public class CreditVerificationWebSocketMessageSender implements WebSocketMessageSender<CreditCheckVerificationMessage> {
    private final SimpMessagingTemplate messagingTemplate;

    public CreditVerificationWebSocketMessageSender(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @Override
    public void send(CreditCheckVerificationMessage data) {
        messagingTemplate.convertAndSend("/topic/credit_notification_sender", data);
    }
}
