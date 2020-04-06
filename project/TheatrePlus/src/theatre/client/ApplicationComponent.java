package theatre.client;

import theatre.server.beans.interfaces.SessionBeanInterface;

import javax.ejb.EJB;

public class ApplicationComponent {
    @EJB
    private SessionBeanInterface bean;

    public void authenticate(String sessionId, String nick) {
        bean.authenticate(sessionId, nick);
    }
}
