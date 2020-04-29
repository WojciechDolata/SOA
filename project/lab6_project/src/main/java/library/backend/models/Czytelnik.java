package library.backend.models;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;


@Getter
@Setter
@Entity
@Table(name = "czytelnicy")
public class Czytelnik {

    @Id
    @GeneratedValue
    @Column(name = "czytelnik_id")
    private Integer id;

    @Column(name = "imie")
    private String imie;

    @Column(name = "nazwisko")
    private String nazwisko;

    @OneToMany(mappedBy = "czytelnik")
    private List<Wypozyczenia> wypozyczenia;

    public Czytelnik(String imie, String nazwisko) {
        this.imie = imie;
        this.nazwisko = nazwisko;
    }

    public Czytelnik() {
    }
}
