package library.backend.services.interfaces;

import library.backend.models.BaseModel;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
@Remote
public interface BaseDatabaseServiceInterface {
    public void insert(BaseModel enitity);
    public List<BaseModel> getByCustomQuery(String type, String query);

}
