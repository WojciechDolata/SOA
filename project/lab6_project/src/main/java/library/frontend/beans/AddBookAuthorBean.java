package library.frontend.beans;


import library.backend.models.Autor;
import library.backend.models.Kategoria;
import library.backend.models.Ksiazka;
import library.backend.models.Pozycja;
import library.backend.services.interfaces.AutorServiceInterface;
import library.backend.services.interfaces.BaseDatabaseServiceInterface;
import library.backend.services.interfaces.KsiazkaServiceInterface;
import library.backend.services.interfaces.PozycjaServiceInterface;
import lombok.Getter;
import lombok.Setter;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import java.io.Serializable;

@ManagedBean(name = "AddBookAuthorBean")
@ViewScoped
@Getter
@Setter
public class AddBookAuthorBean implements Serializable {
    @EJB
    private BaseDatabaseServiceInterface baseDatabaseService;

    @EJB
    private KsiazkaServiceInterface ksiazkaService;

    @EJB
    private PozycjaServiceInterface pozycjaService;

    @EJB
    private AutorServiceInterface autorService;

    public AddBookAuthorBean() {
    }

    private Integer bookCount = 1;
    private Ksiazka ksiazka = new Ksiazka();
    private Autor autor = new Autor();
    private Kategoria kategoria = new Kategoria();

    public String addBook() {
        ksiazka.setAutor(autor);
        ksiazka.setKategoria(kategoria);
        ksiazkaService.insert(ksiazka);
        for(int i = 0; i<bookCount; i++) {
            Pozycja newPozycja = new Pozycja();
            newPozycja.setKsiazka(ksiazka);
            pozycjaService.insert(newPozycja);
        }
        ksiazka = new Ksiazka();
        autor = new Autor();
        kategoria = new Kategoria();

        return "dodawanie";
    }

    public String addAuthor() {
        autorService.insert(autor);
        autor = new Autor();
        return "dodawanie";
    }

}
