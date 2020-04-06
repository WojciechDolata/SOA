package theatre.server.beans.interfaces;

import theatre.server.models.Ticket;

import javax.ejb.Remote;
import javax.ejb.Stateless;

@Remote
@Stateless
public interface PurchaseServiceInterface {
    public Ticket purchaseTicket(Integer userId, Integer row, Integer col, Integer
                                 playId);
}
