package library.backend.services.messaging;

import javax.annotation.Resource;
import javax.ejb.Remote;
import javax.ejb.Singleton;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.Queue;

@Singleton
public class MessageSendingService implements MessageSendingServiceInterface {
//
    @Resource(mappedName = "java:/jms/queue/SOA_Test")
    private Queue queue;
//
    @Inject
    JMSContext context;

    public MessageSendingService() {
    }

    public void sendMessage(String message) {
        try {
            context.createProducer().send(queue, message);
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }
}
