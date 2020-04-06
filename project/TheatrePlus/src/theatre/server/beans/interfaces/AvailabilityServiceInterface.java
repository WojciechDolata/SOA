package theatre.server.beans.interfaces;

import javax.ejb.Remote;
import javax.ejb.Stateless;

@Remote
@Stateless
public interface AvailabilityServiceInterface {
    public boolean isAvailable(int row, int col);

    public void changeAvailability(int row, int col);
}
