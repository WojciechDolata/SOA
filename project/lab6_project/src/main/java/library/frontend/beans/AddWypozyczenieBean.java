package library.frontend.beans;


import library.backend.models.Czytelnik;
import library.backend.models.Ksiazka;
import library.backend.models.Pozycja;
import library.backend.models.Wypozyczenie;
import library.backend.services.interfaces.*;
import lombok.Getter;
import lombok.Setter;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.Calendar;

@ManagedBean(name = "AddWypozyczenieBean")
@ViewScoped
@Getter
@Setter
public class AddWypozyczenieBean implements Serializable {
    @EJB
    private WypozyczeniaServiceInterface wypozyczeniaService;

    @EJB
    private BaseDatabaseServiceInterface baseDatabaseService;

    @EJB
    private KsiazkaServiceInterface ksiazkaService;

    @EJB
    private PozycjaServiceInterface pozycjaService;

    @EJB
    private AutorServiceInterface autorService;

    private Wypozyczenie wypozyczenie = new Wypozyczenie();
    private Ksiazka ksiazka = new Ksiazka();
    private Czytelnik czytelnik = new Czytelnik();
    private Pozycja pozycja = new Pozycja();

    public AddWypozyczenieBean() {
    }

    public String returnPozycja() {
        pozycjaService.returnPozycja(pozycja.getPozycja_id());
        pozycja = new Pozycja();
        return "wypozyczenie";
    }

    public String addWypozyczenie() {

        java.util.Date javaDate = new java.util.Date();
        Date sqlDate = new java.sql.Date(javaDate.getTime());

        wypozyczenie.setWypozyczenieOd(sqlDate);

        Calendar c = Calendar.getInstance();
        c.setTime(sqlDate);
        c.add(Calendar.DATE, 14);
        sqlDate = new Date(c.getTimeInMillis());

        wypozyczenie.setWypozyczenieDo(sqlDate);

        int success = pozycjaService.loanPozycja(ksiazka, czytelnik, wypozyczenie);

        ksiazka = new Ksiazka();
        czytelnik = new Czytelnik();
        wypozyczenie = new Wypozyczenie();

        if(success == -1) {
            return "brakPozycji";
        }
        return "wypozyczenie";
    }
}
