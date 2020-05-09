package library.backend.services.messaging;

import javax.ejb.Remote;
import javax.ejb.Singleton;
import javax.ejb.Stateless;

@Singleton
public interface MessageSendingServiceInterface {
    public void sendMessage(String message);
}
