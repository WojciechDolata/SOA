package library.backend.models;


import lombok.Getter;
import lombok.Setter;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.*;
import java.util.List;

@ManagedBean(name = "Kategoria")
@ViewScoped
@Getter
@Setter
@Entity
@Table(name = "kategorie")
public class Kategoria extends BaseModel {

    @Id
    @GeneratedValue
    @Column(name = "kategoria_id")
    private Integer kategoria_id;

    @Column(name = "typ")
    private String typ;

    @OneToMany(mappedBy = "kategoria", cascade = CascadeType.ALL)
    private List<Ksiazka> ksiazki;

    public Kategoria() {
    }
}
