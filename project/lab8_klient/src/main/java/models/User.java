package models;


import lombok.Getter;
import lombok.Setter;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.io.Serializable;


@ManagedBean(name = "User")
@RequestScoped
@Getter
@Setter
public class User implements Serializable {
    private Integer user_id;
    private String name;
    private String image_uri;
    private Integer age;

    public User() {
    }
}
