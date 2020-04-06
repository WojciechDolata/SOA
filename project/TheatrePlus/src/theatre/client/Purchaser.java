package theatre.client;

import theatre.server.beans.SessionBean;
import theatre.server.beans.interfaces.SessionBeanInterface;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;
import java.util.Map;

@ManagedBean(name = "Purchaser")
@ViewScoped
public class Purchaser extends ApplicationComponent {

    @EJB
    private SessionBeanInterface sessionBean;


    @ManagedProperty(value="#{ErrorManager}")
    ErrorManager errorManager;

    public void setErrorManager(ErrorManager errorManager) {
        this.errorManager = errorManager;
    }

    public String purchase(SeatLocation seatLocation) {

        FacesContext fCtx = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fCtx.getExternalContext().getSession(false);
        String sessionId = session.getId();

        Map<String, Object> cookies = FacesContext.getCurrentInstance().getExternalContext().getRequestCookieMap();
        Cookie cookieUid = (Cookie) cookies.get("UID");
        String userId = cookieUid.getValue();
        try {
            sessionBean.authenticate(sessionId, userId);
            sessionBean.purchaseTicket(userId, seatLocation.getRow(), seatLocation.getColumn(), 0);

        } catch (RuntimeException e) {
            errorManager.setMessage(e.getMessage());
            errorManager.setLastPage("purchaseForm");
            return errorManager.goToError();
        }
        return "purchaseSuccessful";
    }
}
