package org.example.credit_notification_sender.sender;

public interface WebSocketMessageSender<T> {
    void send(T data);
}
