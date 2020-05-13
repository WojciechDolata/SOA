package library.backend.services.messaging;

import javax.ejb.Singleton;

@Singleton
public interface MessageSendingServiceInterface {
    void sendMessage(String message);
}
