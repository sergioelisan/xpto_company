package nonreal.xpto.rest;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * @author sergio lisan <sergiolisan@gmail.com>
 */
@ApplicationPath("/xpto")
public class XPTORESTfulAPI extends Application {

    public Set<Class<?>> getClasses() {
        var classes = new HashSet<Class<?>>();
        classes.add(XPTORestImpl.class);

        return classes;
    }
}
