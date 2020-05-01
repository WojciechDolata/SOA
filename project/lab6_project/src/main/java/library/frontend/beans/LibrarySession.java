package library.frontend.beans;


import library.backend.models.Autor;
import library.backend.models.Ksiazka;
import library.backend.services.interfaces.AutorServiceInterface;
import library.backend.services.interfaces.KsiazkaServiceInterface;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

@ManagedBean(name = "LibraryBean")
@RequestScoped
@Getter
@Setter
public class LibrarySession implements Serializable {

    @EJB
    KsiazkaServiceInterface ksiazkaService;

    @EJB
    AutorServiceInterface autorService;

    private List<Ksiazka> allBooks = new LinkedList<>();
    private Autor topAuthor = new Autor();

    public LibrarySession() {
    }

    @PostConstruct
    private void postConstruct() {
        fetchData();
    }


    public void fetchData() {
        setAllBooks(ksiazkaService.getAll());
        setTopAuthor(autorService.getMostPopular());
    }

}
