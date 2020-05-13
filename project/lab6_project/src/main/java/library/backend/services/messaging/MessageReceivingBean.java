package library.backend.services.messaging;

import library.backend.services.interfaces.ApplicationMessageServiceInterface;
import lombok.Getter;
import lombok.Setter;

import javax.ejb.*;
import javax.faces.bean.ManagedProperty;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;


//@ManagedBean(name = "MessageBean")
//@ApplicationScoped
@Getter
@Setter
//@Singleton
//@Startup
@Remote
@MessageDriven(mappedName = "jms/Queue", activationConfig = {
        @ActivationConfigProperty(
                propertyName = "acknowledgeMode",
                propertyValue = "Auto-acknowledge"),
        @ActivationConfigProperty(
                propertyName = "destination",
                propertyValue = "java:/jms/queue/SOA_Test"),
        @ActivationConfigProperty(
                propertyName = "destinationType",
                propertyValue = "javax.jms.Queue")
})
public class MessageReceivingBean implements MessageListener {

    @EJB
    ApplicationMessageServiceInterface applicationMessageService;

    @Override
    public void onMessage(Message message) {
        try {
            String messageString = message.getBody(String.class);
            System.out.println("Message received: " + messageString);
            if(messageString.contains("UNSUCCESSFUL LOAN")) {
                applicationMessageService.addLoanAttempt(messageString);
            } else if (messageString.contains("RETURN")) {
                applicationMessageService.addReturn(messageString);
            }
        } catch (JMSException ex) {
            System.out.println("Error receiving message.");
        }
    }
}
