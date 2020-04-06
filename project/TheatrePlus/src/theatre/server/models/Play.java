package theatre.server.models;

import java.io.Serializable;
import java.util.List;

public class Play implements Serializable {
    private List<Seat> seatList;
    private Integer playId;
    private static Integer maxCol = 8;
    private static Integer maxRow = 10;

    public Play(List<Seat> seatList, Integer playId) {
        this.seatList = seatList;
        this.playId = playId;
    }

    public List<Seat> getSeatList() {
        return seatList;
    }

    public Seat getSeat(int row, int col) {
        if(row > maxRow)
            throw new RuntimeException("Row number exeeds 10.");
        else if(col > maxCol)
            throw new RuntimeException("Column number exeeds 8.");
        return this.seatList.get((row-1)*col + col - 1);
    }

    public void setSeatList(List<Seat> seatList) {
        this.seatList = seatList;
    }

    public Integer getPlayId() {
        return playId;
    }

    public void setPlayId(Integer playId) {
        this.playId = playId;
    }
}
