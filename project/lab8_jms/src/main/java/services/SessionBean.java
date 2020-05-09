package services;


import lombok.Getter;
import lombok.Setter;
import models.Message;
import models.Topic;
import models.User;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
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
    private List<String> topics = new LinkedList<>();
    private Map<String, List<String>> messages = new HashMap<>();

    @ManagedProperty(value = "#{JMSQueueService}")
    private JMSQueueService jmsQueueService;

    public void addTopic(Topic topic) {
        topics.add(topic.getTitle());
        jmsQueueService.addTopic(topic.getTitle());
    }


    public String addMessage(Message message) {
        jmsQueueService.sendMessage(message.getTopic() + "@" + user.getNick() + " says: " + message.getText());
        return "dodawanie";
    }

    public String fetchMessages() {
        jmsQueueService.receiveMessages();
        messages = jmsQueueService.getTopicMessagesMap(topics);
        return "dodawanie";
    }

    public SessionBean() {
    }


}
