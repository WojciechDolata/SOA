package services;

import javax.annotation.Resource;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.Queue;
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

    private Map<String, List<String>> topicMessagesMap = new HashMap<>();

    public Map<String, List<String>> getTopicMessagesMap(List<String> topics) {
        Map<String, List<String>> returnable = new HashMap<>();
        for (var topic : topics) {
            returnable.putIfAbsent(topic, topicMessagesMap.get(topic));
        }
        return returnable;
    }

    public void addTopic(String name) {
        topicMessagesMap.putIfAbsent(name, new LinkedList<>());
    }

    public void receiveMessages() {
        String message;
        while (true) {
            message = context.createConsumer(queue).receiveBodyNoWait(String.class);
            try {
                var words = message.split("@", 2);
                topicMessagesMap.get(words[0]).add(words[1]);
            } catch (NullPointerException ex) {
                break;
            }
        }
    }

    public void sendMessage(String message) {
        try {
            context.createProducer().send(queue, message);
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }

}
