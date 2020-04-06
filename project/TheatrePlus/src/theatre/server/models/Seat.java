package theatre.server.models;

import java.io.Serializable;

public class Seat implements Serializable {
    private Integer row;
    private Integer col;
    private Integer price;
    private Boolean available = true;


    public Seat(Integer row, Integer col, Integer price) {
        this.row = row;
        this.col = col;
        this.price = price;
    }

    public Integer getRow() {
        return row;
    }

    public void setRow(Integer row) {
        this.row = row;
    }

    public Integer getCol() {
        return col;
    }

    public void setCol(Integer col) {
        this.col = col;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }


}
