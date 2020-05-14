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
//    public ApplicationClass() {
//        BeanConfig beanConfig = new BeanConfig();
//        beanConfig.setVersion("1.0.2");
//        beanConfig.setSchemes(new String[]{"http"});
//        beanConfig.setHost("localhost:8080");
//        beanConfig.setBasePath("/lab8_jax_rs-1.0-SNAPSHOT/myapp");
//        beanConfig.setResourcePackage("io.swagger.resources");
//        beanConfig.setScan(true);
//    }

    public ApplicationClass() {
//        BeanConfig beanConfig = new BeanConfig();
//        beanConfig.setVersion("1.0.2");
//        beanConfig.setBasePath("/mypath");
//        beanConfig.setBasePath("http://localhost:8080/lab8_jax_rs-1.0-SNAPSHOT/myapp");
////        beanConfig.setResourcePackage("io.swagger.resources");
//        beanConfig.setResourcePackage("backend.controllers");
//        beanConfig.setScan(true);

        BeanConfig beanConfig = new BeanConfig();
        beanConfig.setVersion("v1");
        beanConfig.setSchemes(new String[]{"http"});
        beanConfig.setHost(System.getProperty("backend.swagger.host", "localhost:8080"));
        beanConfig.setBasePath(System.getProperty("backend.swagger.basepath", "lab8_jax_rs-1.0-SNAPSHOT/myapp/"));
        beanConfig.setResourcePackage("backend.controllers");
        beanConfig.setScan(true);
    }

    @Override
    public Set<Class<?>> getClasses() {
        HashSet<Class<?>> set = new HashSet<Class<?>>();
        set.add(MovieController.class);
        set.add(UserController.class);
        set.add(OsobyController.class);
//        set.add(com.wordnik.swagger.jaxrs.listing.ApiListingResource.class);
//        set.add(com.wordnik.swagger.jaxrs.listing.ApiDeclarationProvider.class);
//        set.add(com.wordnik.swagger.jaxrs.listing.ApiListingResourceJSON.class);
//        set.add(com.wordnik.swagger.jaxrs.listing.ResourceListingProvider.class);
        set.add(io.swagger.jaxrs.listing.ApiListingResource.class);
        set.add(io.swagger.jaxrs.listing.SwaggerSerializers.class);
        return set;
    }
}
