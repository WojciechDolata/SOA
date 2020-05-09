package models;

import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
public class User {
    private String nick;
    private Integer passwordHash;
    private List<Topic> subscribedTopics = new LinkedList<>();

    public User(String nick, String password) {
        this.nick = nick;
        this.passwordHash = password.hashCode();
    }

    public void addTopic(Topic topic) {
        subscribedTopics.add(topic);
    }

}
