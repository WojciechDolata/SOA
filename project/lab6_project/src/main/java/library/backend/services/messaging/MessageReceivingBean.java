package library.backend.services.messaging;

import lombok.Getter;
import lombok.Setter;

import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;


//@ManagedBean(name = "MessageBean")
//@ApplicationScoped
//@Getter
//@Setter
//@Singleton
//@Startup
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


    @Override
    public void onMessage(Message message) {
        try {
            System.out.println("Message received: " + message.getBody(String.class));
        } catch (JMSException ex) {
            System.out.println("Error receiving message.");
        }
    }
}
