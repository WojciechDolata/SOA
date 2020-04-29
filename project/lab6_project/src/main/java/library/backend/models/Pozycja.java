package library.backend.models;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "pozycje")
public class Pozycja {

    @Id
    @GeneratedValue
    @Column(name = "pozycja_id")
    private Integer id;

    @Column(name = "status")
    private String status;

    @ManyToOne
    @JoinColumn(name = "ksiazka_id")
    private Ksiazka ksiazka;

    @OneToMany(mappedBy = "pozycja", cascade = CascadeType.ALL)
    private List<Wypozyczenia> wypozyczenia;

    public Pozycja() {
    }
}
