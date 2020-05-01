package library.backend.services.interfaces;

import library.backend.models.BaseModel;

import javax.ejb.Remote;
import javax.ejb.Stateless;

@Stateless
@Remote
public interface BaseDatabaseServiceInterface {
    public void insert(BaseModel enitity);
}
