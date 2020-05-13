package library.backend.services.interfaces;


import library.backend.models.Czytelnik;
import lombok.Getter;
import lombok.Setter;

import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.util.List;

@Singleton
@Startup
public interface ApplicationMessageServiceInterface {
    void addLoanAttempt(String message);
    void addReturn(String message);

    List<String> getMessagesForUser(String imie, String nazwisko);
}
