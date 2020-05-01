package library.backend.models;


import lombok.Getter;
import lombok.Setter;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.*;
import java.util.List;

@ManagedBean(name = "Czytelnik")
@ViewScoped
@Getter
@Setter
@Entity
@Table(name = "czytelnicy")
public class Czytelnik extends BaseModel {

    @Id
    @GeneratedValue
    @Column(name = "czytelnik_id")
    private Integer czytelnik_id;

    @Column(name = "imie")
    private String imie;

    @Column(name = "nazwisko")
    private String nazwisko;

    @OneToMany(mappedBy = "czytelnik")
    private List<Wypozyczenie> wypozyczenia;

    public Czytelnik(String imie, String nazwisko) {
        this.imie = imie;
        this.nazwisko = nazwisko;
    }

    public Czytelnik() {
    }

    @Override
    public String toString() {
        return "Czytelnik{" +
                "czytelnik_id=" + czytelnik_id +
                ", imie='" + imie + '\'' +
                ", nazwisko='" + nazwisko + '\'' +
                '}';
    }
}
