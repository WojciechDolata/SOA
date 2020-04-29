package library.backend.models;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "kategorie")
public class Kategoria {

    @Id
    @GeneratedValue
    @Column(name = "kategoria_id")
    private Integer id;

    @Column(name = "typ")
    private String typ;

    @OneToMany(mappedBy = "kategoria", cascade = CascadeType.ALL)
    private List<Ksiazka> ksiazki;

    public Kategoria() {
    }
}
