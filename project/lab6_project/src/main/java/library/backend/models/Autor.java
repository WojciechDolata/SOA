package library.backend.models;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "autorzy")
public class Autor {

    @Id
    @GeneratedValue
    @Column(name = "autor_id")
    private Integer id;

    @Column(name = "imie")
    private String imie;

    @Column(name = "nazwisko")
    private String nazwisko;

    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL)
    private List<Ksiazka> ksiazki;

    public Autor(String imie, String nazwisko) {
        this.imie = imie;
        this.nazwisko = nazwisko;
    }

    public Autor() {
    }
}
