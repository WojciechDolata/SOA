package library.frontend.beans;


import library.backend.models.Czytelnik;
import library.backend.services.interfaces.ApplicationMessageServiceInterface;
import lombok.Getter;
import lombok.Setter;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.LinkedList;
import java.util.List;

@RequestScoped
@ManagedBean(name = "ProfileBean")
@Getter
@Setter
public class ProfileBean {

    private List<String> messages = new LinkedList<>();


    @EJB
    ApplicationMessageServiceInterface applicationMessageService;

    public ProfileBean() {
    }

    public String fetchMessages(Czytelnik czytelnik) {
        messages = applicationMessageService.getMessagesForUser(czytelnik.getImie(), czytelnik.getNazwisko());
        return "profile";
    }
}
