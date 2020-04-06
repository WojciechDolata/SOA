package theatre.server.beans.interfaces;

import theatre.server.models.*;

import javax.ejb.Remote;
import javax.ejb.Singleton;
import java.util.List;

@Remote
@Singleton
public interface SessionBeanInterface {
    public Ticket purchaseTicket(String nick, Integer row, Integer col, Integer
            playId);

    public void addUser(String nick, String password, Integer cash);

    public boolean login(String nick, Integer passHash, String sid);

    public void logout(String nick);

    public void authenticate(String sid, String nick);

    public Play getPlay();

    public List<User> getUsers();

    public void setPlay(Play play);
}
