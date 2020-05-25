package backend;

import backend.controllers.MovieController;
import backend.controllers.OsobyController;
import backend.controllers.UserController;
//import com.wordnik.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.config.BeanConfig;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/myapp")
public class ApplicationClass extends Application {

    public ApplicationClass() {
        BeanConfig beanConfig = new BeanConfig();
        beanConfig.setVersion("1.0.0");
        beanConfig.setBasePath("/myapp");
        beanConfig.setResourcePackage("backend");
        beanConfig.setScan(true);
    }

    @Override
    public Set<Class<?>> getClasses() {
        HashSet<Class<?>> set = new HashSet<Class<?>>();
        set.add(MovieController.class);
        set.add(UserController.class);
        set.add(OsobyController.class);
        set.add(io.swagger.jaxrs.listing.ApiListingResource.class);
        set.add(io.swagger.jaxrs.listing.SwaggerSerializers.class);
        return set;
    }
}
