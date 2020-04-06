package theatre.client;


import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "ErrorManager")
@SessionScoped
public class ErrorManager {
    private String message;
    private String lastPage;


    public String getLastPage() {
        return this.lastPage;
    }

    public void setLastPage(String lastPage) {
        this.lastPage = lastPage;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ErrorManager(String message, String lastPage) {
        this.message = message;
        this.lastPage = lastPage;
    }

    public ErrorManager() {
    }

    public String goToError() {
        return "error";
    }
}
