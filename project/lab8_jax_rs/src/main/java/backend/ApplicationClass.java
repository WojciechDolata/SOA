package backend;

import backend.controllers.MovieController;
import backend.controllers.OsobyController;
import backend.controllers.UserController;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/myapp")
public class ApplicationClass extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        HashSet<Class<?>> set = new HashSet<Class<?>>();
        set.add(MovieController.class);
        set.add(UserController.class);
        set.add(OsobyController.class);
        return set;
    }
}
