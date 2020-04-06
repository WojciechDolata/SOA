package theatre.server.beans;

import theatre.server.beans.interfaces.PurchaseServiceInterface;
import theatre.server.beans.interfaces.SessionBeanInterface;
import theatre.server.models.*;

import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Singleton
@Startup
public class SessionBean implements SessionBeanInterface {

    private List<User> users;
    private Map<Integer,String> loggedUsers = new HashMap<>();
    private Play play;

    @EJB
    private PurchaseServiceInterface purchaseService;

    public SessionBean() {
        generateMockData(10,8);
    }

    private void generateMockData(int numberOfRows, int numberOfCols) {
        var tmpUserList = new ArrayList<User>();
        tmpUserList.add(new User("adam", "adam1", 200));
        tmpUserList.add(new User("admin", "admin1", 200));
        tmpUserList.add(new User("user", "user1", 10));
        this.users = tmpUserList;

        var tmpSeatList = new ArrayList<Seat>();
        for (int i = 0; i < numberOfRows; i++){
            for (int j = 0; j < numberOfCols; j++){
                tmpSeatList.add(new Seat(i, j, i > 5 ? 20 : 15));
            }
        }
        this.play = new Play(tmpSeatList, 1);
    }

    private Integer getUserIdForNick(String nick) {
        for( var u : users) {
            if (u.getNick().equals(nick)){
                return u.getId();
            }
        }
        return null;
    }

    @Override
    public Ticket purchaseTicket(String nick, Integer row, Integer col, Integer playId) throws RuntimeException {
        try {
            return purchaseService.purchaseTicket(getUserIdForNick(nick), row, col, playId);
        } catch (RuntimeException e) {
            throw e;
        }
    }

    @Override
    public void addUser(String nick, String password, Integer cash) {
        for(var u : users) {
            if(u.getNick().equals(nick))
                throw new RuntimeException("User already exists.");
        }
        this.users.add(new User(nick, password, cash));
    }

    @Override
    public boolean login(String nick, Integer passHash, String sid) {
        for ( var u: users) {
            if (u.getNick().equals(nick)) {
                if (u.getPasswordHash().equals(passHash)) {
                    loggedUsers.put(u.getId(), sid);
                    return true;
                }
                return false;
            }
        }
        return false;
    }

    @Override
    public void logout(String nick) {
        loggedUsers.remove(getUserIdForNick(nick));
    }

    @Override
    public void authenticate(String sid, String nick) {
        if(!loggedUsers.get(getUserIdForNick(nick)).equals(sid)) {
            throw new RuntimeException("Wrong credentials!");
        }
    }

    @Override
    public List<User> getUsers() {
        return users;
    }

    @Override
    public void setPlay(Play play) {
        this.play = play;
    }

    @Override
    public Play getPlay() {
        return play;
    }
}
