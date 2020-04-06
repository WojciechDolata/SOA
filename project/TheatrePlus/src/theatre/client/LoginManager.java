package theatre.client;
import theatre.server.beans.interfaces.SessionBeanInterface;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;
import java.util.Map;

@ManagedBean(name = "LoginManager")
@ViewScoped
public class LoginManager {

    @EJB
    private SessionBeanInterface bean;


    @ManagedProperty(value="#{ErrorManager}")
    ErrorManager errorManager;

    public void setErrorManager(ErrorManager errorManager) {
        this.errorManager = errorManager;
    }

    public String tryToLogin(LoginDetails loginDetails) {
        FacesContext fCtx = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fCtx.getExternalContext().getSession(false);
        String sessionId = session.getId();

        if(bean.login(loginDetails.getNick(), loginDetails.getPassword().hashCode(), sessionId)) {
            FacesContext.getCurrentInstance().getExternalContext().addResponseCookie("SID", sessionId, null);
            FacesContext.getCurrentInstance().getExternalContext().addResponseCookie("UID", loginDetails.getNick(), null);
            return "purchaseForm";
        }
        errorManager.setMessage("Login failed, wrong credentials");
        errorManager.setLastPage("login");
        return errorManager.goToError();
    }

    public String logout(){
        var context = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, Object> cookies = context.getRequestCookieMap();
        Cookie uid = (Cookie) cookies.get("UID");
        Cookie sid = (Cookie) cookies.get("SID");
        uid.setMaxAge(0);
        sid.setMaxAge(0);
        bean.logout(uid.getValue());

        return "login";
    }

    public String addUser(LoginDetails loginDetails) {
        try {
            bean.addUser(loginDetails.getNick(), loginDetails.getPassword(), 100);
        } catch (RuntimeException e) {
            errorManager.setLastPage("login");
            errorManager.setMessage(e.getMessage());
            return "error";
        }
        return "login";
    }
}