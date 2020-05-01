package library.frontend.beans;


import library.backend.LibraryManagerInterface;

import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.io.Serializable;

@ManagedBean(name = "library")
@ApplicationScoped
public class LibrarySession implements Serializable {

    @EJB
    LibraryManagerInterface libraryManager;

    public LibrarySession() {
    }
}
