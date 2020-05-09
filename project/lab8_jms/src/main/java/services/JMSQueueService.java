package services;

import javax.annotation.Resource;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;
import javax.jms.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


@ManagedBean(name = "JMSQueueService")
@ApplicationScoped
public class JMSQueueService {

    @Resource(mappedName = "java:/jms/queue/SOA_Test")
    private Queue queue;

    @Inject
    JMSContext context;

    private Map<String,Topic> topics = new HashMap<>();

//    Session session;

//    public JMSQueueService(Session session) {
//        this.session = co
//    }

//    public void sendMessage(String txt) {
//        try {
//            context.createProducer().send(queue, txt);
//        }
//        catch (Exception exc) {
//            exc.printStackTrace();
//        }
//    }

    public void sendMessage(String message, String topicName) {
        try {
            var producer = context.createProducer();
            producer.send(topics.get(topicName), message);
        }
        catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    public JMSConsumer getConsumer(Topic topic) {

        try {
            return context.createSharedConsumer(topic, "@" + topic.getTopicName() + ": ");
        } catch (JMSException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    public void createTopic(Topic topic) {
        try {
            topics.put(topic.getTopicName(), context.createTopic(topic.getTopicName()));
        } catch (JMSException ex) {
            System.out.println(ex.getMessage());
        }
    }


    public String receiveMessage(String topicName) {
//        var consumer = context.createConsumer(topics.get(topicName));
//        consumer.receiveBNoWait()
        return context.createConsumer(topics.get(topicName)).receiveBodyNoWait(String.class);
    }
}
