package library.backend.models;


import lombok.Getter;
import lombok.Setter;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.*;
import java.util.List;

@ManagedBean(name = "Pozycja")
@ViewScoped
@Getter
@Setter
@Entity
@Table(name = "pozycje")
public class Pozycja extends BaseModel {

    @Id
    @GeneratedValue
    @Column(name = "pozycja_id")
    private Integer pozycja_id;

    @Column(name = "status")
    private String status;

    @Override
    public String toString() {
        return "Pozycja{" +
                "pozycja_id=" + pozycja_id +
                ", status='" + status + '\'' +
                '}';
    }

    @ManyToOne
    @JoinColumn(name = "ksiazka_id")
    private Ksiazka ksiazka;

    @OneToMany(mappedBy = "pozycja", cascade = CascadeType.ALL)
    private List<Wypozyczenie> wypozyczenia;

    public Pozycja() {
        setStatus("ok");
    }
}
