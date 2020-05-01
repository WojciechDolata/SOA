package library.frontend.beans;


import lombok.Getter;
import lombok.Setter;

import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import java.io.Serializable;
import java.sql.Date;

@ManagedBean(name = "Parameters")
@RequestScoped
@Getter
@Setter
public class ParametersModel implements Serializable {
    private String autorNazwisko, autorImie, czytelnikNazwisko, czytelnikImie, kategoriaTyp, ksiazkaTytul, ksiazkaNumer, pozycjaStatus;
    private Date wypozyczenieOd, wypozyczenieDo;

    public ParametersModel() {
    }
}
