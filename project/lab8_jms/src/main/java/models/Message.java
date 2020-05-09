package models;

import lombok.Getter;
import lombok.Setter;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;

@ViewScoped
@ManagedBean(name = "Message")
@Getter
@Setter
public class Message implements Serializable {
    private String text;
    private String topic;

    public Message() {
    }
}
