package theatre.server.models;

import java.io.Serializable;

public class Ticket implements Serializable {
    private Integer userId;
    private Seat seat;
    private Integer playId;

    public Ticket(Integer userId, Seat seat, Integer playId) {
        this.userId = userId;
        this.seat = seat;
        this.playId = playId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    public Integer getPlayId() {
        return playId;
    }

    public void setPlayId(Integer playId) {
        this.playId = playId;
    }
}
