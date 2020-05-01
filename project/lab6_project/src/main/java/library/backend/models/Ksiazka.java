package library.backend.models;


import lombok.Getter;
import lombok.Setter;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.*;
import java.util.List;

@ManagedBean(name = "Ksiazka")
@ViewScoped
@Getter
@Setter
@Entity
@Table(name = "ksiazki")
public class Ksiazka extends BaseModel {

    @Id
    @GeneratedValue
    @Column(name = "ksiazka_id")
    private Integer ksiazka_id;

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

    @OneToMany(mappedBy = "ksiazka", cascade = CascadeType.ALL)
    private List<Pozycja> pozycje;

    @Override
    public String toString() {
        return "Ksiazka{" +
                "ksiazka_id=" + ksiazka_id +
                ", tytul='" + tytul + '\'' +
                ", numerISBN='" + numerISBN + '\'' +
                '}';
    }

    public Ksiazka() {
    }
}
