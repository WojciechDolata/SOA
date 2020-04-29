package library.backend.models;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

@Getter
@Setter
@Entity
@Table(name = "wypozyczenia")
public class Wypozyczenia {

    @Id
    @GeneratedValue
    @Column(name = "wypozyczenia_id")
    private Integer id;

    @Column(name = "wypozyczenie_od")
    private Date wypozyczenieOd;
    @Column(name = "wypozyczenie_do")
    private Date wypozyczenieDo;


    @ManyToOne
    @JoinColumn(name = "pozycja_id")
    private Pozycja pozycja;

    @ManyToOne
    @JoinColumn(name = "czytelnik_id")
    private Czytelnik czytelnik;

    public Wypozyczenia() {
    }
}
