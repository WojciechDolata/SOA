package library.frontend.beans;


import lombok.Getter;
import lombok.Setter;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import java.io.Serializable;

@ManagedBean(name = "SearchParametersBean")
@SessionScoped
@Getter
@Setter
public class SearchParametersBean implements Serializable {

    @ManagedProperty(value="#{Parameters}")
    private ParametersModel parameters;

    private String type;
    private String query;

    public SearchParametersBean() {
    }

    private String getLetterFromType(){
        switch (type){
            case "autor": return "a";
            case "czytelnik": return "c";
            case "kategoria": return "ka";
            case "ksiazka": return "ks";
            case "pozycja": return "p";
            default: return "w";
        }
    }

    public String buildQuery() {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT DISTINCT ").append(getLetterFromType());
        sb.append(" FROM Autor a JOIN a.ksiazki ks JOIN ks.pozycje p JOIN  p.wypozyczenia w JOIN w.czytelnik c JOIN ks.kategoria ka WHERE 1=1 ");
        if(!parameters.getAutorImie().equals("")){
            sb.append("AND a.imie LIKE '").append(parameters.getAutorImie()).append("' ");
        }
        if(!parameters.getAutorNazwisko().equals("")){
            sb.append("AND a.nazwisko LIKE '").append(parameters.getAutorNazwisko()).append("' ");
        }
        if(!parameters.getCzytelnikImie().equals("")){
            sb.append("AND c.imie LIKE '").append(parameters.getCzytelnikImie()).append("' ");
        }
        if(!parameters.getCzytelnikNazwisko().equals("")){
            sb.append("AND c.nazwisko LIKE '").append(parameters.getCzytelnikNazwisko()).append("' ");
        }
        if(!parameters.getKategoriaTyp().equals("")){
            sb.append("AND ka.typ LIKE '").append(parameters.getKategoriaTyp()).append("' ");
        }
        if(!parameters.getKsiazkaTytul().equals("")){
            sb.append("AND ks.tytul LIKE '").append(parameters.getKsiazkaTytul()).append("' ");
        }
        if(!parameters.getKsiazkaNumer().equals("")){
            sb.append("AND ks.numer LIKE '").append(parameters.getKsiazkaNumer()).append("' ");
        }
        if(!parameters.getPozycjaStatus().equals("")){
            sb.append("AND p.status LIKE '").append(parameters.getPozycjaStatus()).append("' ");
        }

        query = sb.toString();

        return "wyniki";
    }
}
