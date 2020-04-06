package theatre.server.beans;

import theatre.server.beans.interfaces.AvailabilityServiceInterface;
import theatre.server.beans.interfaces.SessionBeanInterface;

import javax.ejb.EJB;
import javax.ejb.Lock;
import javax.ejb.Stateless;

@Stateless
public class AvailabilityService implements AvailabilityServiceInterface {

    @EJB
    private SessionBeanInterface sessionBean;

    @Lock
    @Override
    public boolean isAvailable(int row, int col) {
        return sessionBean.getPlay().getSeat(row, col).getAvailable();
    }

    @Lock
    @Override
    public void changeAvailability(int row, int col) {
        var tmpPlay = sessionBean.getPlay();
        var tmpSeats = tmpPlay.getSeatList();
        var tmpSeat = tmpPlay.getSeat(row, col);
        tmpSeat.setAvailable(false);
        tmpSeats.set((row-1)*col + col - 1, tmpSeat);
        tmpPlay.setSeatList(tmpSeats);
        sessionBean.setPlay(tmpPlay);
    }
}
