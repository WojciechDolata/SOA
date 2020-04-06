import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "Column")
@RequestScoped
public class Column {
    private String name;
    private String exactVal;
    private Integer from;
    private Integer to;

    public Column() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExactVal() {
        return exactVal;
    }

    public void setExactVal(String exactVal) {
        this.exactVal = exactVal;
    }

    public Integer getFrom() {
        return from;
    }

    public void setFrom(Integer from) {
        this.from = from;
    }

    public Integer getTo() {
        return to;
    }

    public void setTo(Integer to) {
        this.to = to;
    }
}
