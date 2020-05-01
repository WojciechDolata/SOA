package library.backend.models;


import lombok.Getter;
import lombok.Setter;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.*;
import java.sql.Date;

@ManagedBean(name = "Wypozyczenie")
@ViewScoped
@Getter
@Setter
@Entity
@Table(name = "wypozyczenia")
public class Wypozyczenie extends BaseModel {

    @Id
    @GeneratedValue
    @Column(name = "wypozyczenia_id")
    private Integer wypozyczenie_id;

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

    public Wypozyczenie() {
    }

    @Override
    public String toString() {
        return "Wypozyczenie{" +
                "wypozyczenie_id=" + wypozyczenie_id +
                ", wypozyczenieOd=" + wypozyczenieOd +
                ", wypozyczenieDo=" + wypozyczenieDo +
                '}';
    }
}
