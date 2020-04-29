package library.backend.models;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "ksiazki")
public class Ksiazka {

    @Id
    @GeneratedValue
    @Column(name = "ksiazka_id")
    private Integer id;

    @Column(name = "tytul")
    private String tytul;

    @Column(name = "number_isbn")
    private String numerISBN;

    @ManyToOne
    @JoinColumn(name = "kategoria_id")
    private Kategoria kategoria;

    @ManyToOne
    @JoinColumn(name = "autor_id")
    private Autor autor;

    @OneToMany(mappedBy = "pozycja", cascade = CascadeType.ALL)
    private List<Pozycja> pozycje;

    public Ksiazka() {
    }
}
