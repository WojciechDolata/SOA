package backend.models;

import lombok.Getter;
import lombok.Setter;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.*;
import javax.swing.text.html.parser.TagElement;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@ManagedBean(name = "User")
@ViewScoped
@Getter
@Setter
@Entity
@Table(name = "Users")
public class User implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Integer user_id;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private Integer age;

    @Column(name = "image_uri")
    private String image_uri;

    @ManyToMany
    @JoinTable(
            name = "users_movies",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "movie_id")
    )
    private Set<Movie> movies = new HashSet<>();

    public User() {
    }

    public User(String name, Integer age, String image_uri) {
        this.name = name;
        this.age = age;
        this.image_uri = image_uri;
    }
    public User(Integer id, String name, Integer age, String image_uri) {
        this.user_id = id;
        this.name = name;
        this.age = age;
        this.image_uri = image_uri;
    }
}
