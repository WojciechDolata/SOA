package library.frontend.beans;


import library.backend.models.BaseModel;
import library.backend.services.interfaces.BaseDatabaseServiceInterface;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.List;

@ManagedBean(name = "CustomSearchBean")
@ViewScoped
@Getter
@Setter
public class CustomSearchBean implements Serializable {

    @EJB
    BaseDatabaseServiceInterface baseDatabaseService;

    @ManagedProperty(value="#{SearchParametersBean}")
    private SearchParametersBean searchParametersBean;

    private List<BaseModel> wynik;

    public CustomSearchBean() {
    }

    @PostConstruct
    private void postConstruct() {
        wynik = baseDatabaseService.getByCustomQuery(searchParametersBean.getType(), searchParametersBean.getQuery());
    }
}
