package backend.models;


import lombok.Getter;
import lombok.Setter;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@ManagedBean(name = "Movie")
@ViewScoped
@Getter
@Setter
@Entity
@Table(name = "Movies")
public class Movie implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "movie_id")
    private Integer movie_id;

    @Column(name = "uri")
    private String uri;

    @Column(name = "title")
    private String title;

    @ManyToMany(mappedBy = "movies")
    private Set<User> users = new HashSet<>();

    public Movie() {
    }

    public Movie(String title, String uri) {
        this.uri = uri;
        this.title = title;
    }

    public Movie(Integer id, String title, String uri) {
        this.movie_id = id;
        this.uri = uri;
        this.title = title;
    }
}
