package models;

import lombok.Getter;
import lombok.Setter;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.jms.JMSException;
import java.io.Serializable;

@ViewScoped
@ManagedBean(name = "Topic")
@Getter
@Setter
public class Topic implements Serializable, javax.jms.Topic {
    private String title;

    public Topic() {
    }

    @Override
    public String getTopicName() throws JMSException {
        return title;
    }
}
