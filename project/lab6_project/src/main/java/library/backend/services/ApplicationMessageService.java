package library.backend.services;

import library.backend.models.Czytelnik;
import library.backend.models.Ksiazka;
import library.backend.services.interfaces.ApplicationMessageServiceInterface;
import library.backend.services.interfaces.CzytelnikServiceInterface;
import library.backend.services.interfaces.KsiazkaServiceInterface;
import lombok.Getter;

import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


@Startup
@Singleton
public class ApplicationMessageService implements ApplicationMessageServiceInterface {
    private List<String> loanAttempts = new LinkedList<>();
    private List<String> returns = new LinkedList<>();

    private Map<Integer, List<String>> messagesForUser = new HashMap<>();
    private Map<Integer, List<Ksiazka>> wantedBooksForUser = new HashMap<>();

    @EJB
    CzytelnikServiceInterface czytelnikService;

    @EJB
    KsiazkaServiceInterface ksiazkaService;

    @Override
    public List<String> getMessagesForUser(String imie, String nazwisko) {
        var czytelnik = czytelnikService.getByName(imie, nazwisko);
        return messagesForUser.get(czytelnik.getCzytelnik_id());
    }

    @Override
    public void addLoanAttempt(String message) {
        var words = message.split("@", 3);
        Ksiazka ksiazka = ksiazkaService.getById(Integer.parseInt(words[1]));
        Czytelnik czytelnik = czytelnikService.getById(Integer.parseInt(words[2]));
        wantedBooksForUser.putIfAbsent(czytelnik.getCzytelnik_id(), new LinkedList<>());
        wantedBooksForUser.get(czytelnik.getCzytelnik_id()).add(ksiazka);
    }

    @Override
    public void addReturn(String message) {
        var words = message.split("@", 2);
        Ksiazka ksiazka = ksiazkaService.getByPozycjaId(Integer.parseInt(words[1]));
        wantedBooksForUser.forEach((czytelnik, wantedBooks) -> {
            for(var bk : wantedBooks) {
                if(bk.getKsiazka_id().equals(ksiazka.getKsiazka_id())) {
                    messagesForUser.putIfAbsent(czytelnik, new LinkedList<>());
                    messagesForUser.get(czytelnik).add(ksiazka.getTytul() + " jest już dostępna.");
                    wantedBooksForUser.get(czytelnik).remove(ksiazka);
                }
            }
        });
    }
}
