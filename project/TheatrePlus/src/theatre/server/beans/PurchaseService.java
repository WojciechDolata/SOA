package theatre.server.beans;

import theatre.server.beans.interfaces.AvailabilityServiceInterface;
import theatre.server.beans.interfaces.PurchaseServiceInterface;
import theatre.server.beans.interfaces.SessionBeanInterface;
import theatre.server.models.Ticket;
import theatre.server.models.User;

import javax.ejb.EJB;
import javax.ejb.Lock;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class PurchaseService implements PurchaseServiceInterface {

    @EJB
    private SessionBeanInterface sessionBean;

    @EJB
    private AvailabilityServiceInterface availabilityService;

    @Lock
    @Override
    public Ticket purchaseTicket(Integer userId, Integer row, Integer col, Integer playId) {
        var user = sessionBean.getUsers().get(userId);
        var seat = sessionBean.getPlay().getSeat(row, col);
        if(user.getMoney() < seat.getPrice()) {
            throw new RuntimeException("You can't afford that seat.");
        }
        else if(!availabilityService.isAvailable(row, col)) {
            throw new RuntimeException("Seat is already taken.");
        }
        else {
            user.setMoney(user.getMoney() - seat.getPrice());
            availabilityService.changeAvailability(row, col);
            return new Ticket(userId, seat, playId);
        }
    }
}
