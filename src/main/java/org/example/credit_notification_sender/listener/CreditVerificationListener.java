package org.example.credit_notification_sender.listener;

import org.example.credit_notification_sender.sender.WebSocketMessageSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import com.example.sop_contracts.messages.CreditCheckVerificationMessage;
import org.springframework.stereotype.Component;

@Component
public class CreditVerificationListener {
    private final WebSocketMessageSender<CreditCheckVerificationMessage> sender;
    private static final Logger logger = LoggerFactory.getLogger(CreditVerificationListener.class);

    public CreditVerificationListener(WebSocketMessageSender<CreditCheckVerificationMessage> sender) {
        this.sender = sender;
    }

    @RabbitListener(queues = "creditVerificationQueue")
    public void receiveCreditVerification(CreditCheckVerificationMessage message) {
        logger.info("Received credit verification message: {}", message);
        sender.send(message);
    }
}
