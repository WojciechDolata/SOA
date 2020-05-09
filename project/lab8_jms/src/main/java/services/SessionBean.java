package services;


import lombok.Getter;
import lombok.Setter;
import models.*;
import models.Message;
import models.Topic;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.jms.*;
import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@ManagedBean(name = "SessionBean")
@SessionScoped
@Getter
@Setter
public class SessionBean implements Serializable {
    private User user;
    private List<String> messages = new LinkedList<>();
    private Map<String, JMSConsumer> consumers = new HashMap<>();
    private Map<String, TopicSubscriber> subs = new HashMap<>();
//    private Map<String, TopicPublisher> consumers = new HashMap<>();

    @ManagedProperty(value="#{JMSQueueService}")
    private JMSQueueService jmsQueueService;

    public void addTopic(Topic topic) {
//        TopicSubscriber topicSubscriber = jmsQueueService.context
        user.addTopic(topic);
        jmsQueueService.createTopic(topic);
        consumers.put(topic.getTitle(), jmsQueueService.getConsumer(topic));
//        subs.putIfAbsent(topic.getTitle(), jmsQueueService.getConsumer(topic).)
//        fetchMessages();
    }


    public String addMessage(Message message) {
        jmsQueueService.sendMessage(message.getText(), message.getTopic());
        fetchMessages();
        return "dodawanie";
    }


    public void fetchMessages() {
        try {
            for (int i = 0; i < user.getSubscribedTopics().size(); i++) {
                MessageConsumer messageConsumer;
                var topic = user.getSubscribedTopics().get(i);
//                var message = jmsQueueService.receiveMessage(topic.getTopicName());
                var message = consumers.get(topic.getTopicName()).receiveBodyNoWait(String.class);
//                var message = consumers.get(topic.getTopicName()).receiveBody(String.class);
                while (message != null) {
                    messages.add("@" + topic.getTopicName() + ": " + message);
                    message = consumers.get(topic.getTopicName()).receiveBodyNoWait(String.class);
                }
            }
        } catch (JMSException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public SessionBean() {
    }


}
