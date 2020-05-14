package models;


import lombok.Getter;
import lombok.Setter;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.io.Serializable;


@ManagedBean(name = "Movie")
@RequestScoped
@Getter
@Setter
public class Movie implements Serializable {
    private Integer movie_id = null;
    private String title;
    private String uri;

    public Movie() {
    }
}
