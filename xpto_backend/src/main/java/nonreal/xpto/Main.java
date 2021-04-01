package nonreal.xpto;

import java.net.InetSocketAddress;
import java.util.EnumSet;

import javax.servlet.DispatcherType;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.servlets.CrossOriginFilter;
import org.glassfish.jersey.servlet.ServletContainer;

public class Main {

    public static void main(String[] args) {
        new Main().init();
    }

    /** */
    public void init() {
        try {
            var add = new InetSocketAddress("0.0.0.0", 8080);

            var server = new Server(add);
            server.setHandler(build());
            server.start();

            System.out.println("ONLINE");

        } catch (Exception e) {
            System.out.println("ERRO AO SUBIR SERVIDOR");
            e.printStackTrace();
        }
    }

    /** */
    public static ServletContextHandler build() {
        var servletCtx = new ServletContextHandler();
        servletCtx.setContextPath("/");

        // (2)
        var servletHolder = new ServletHolder(ServletContainer.class);
        servletHolder.setInitParameter("javax.ws.rs.Application", "nonreal.xpto.rest.XPTORESTfulAPI");
        servletCtx.addServlet(servletHolder, "/*");

        // baboseira protocolar do caralho a quatro
        var filterHolder = servletCtx.addFilter(CrossOriginFilter.class, "/*", EnumSet.of(DispatcherType.REQUEST));
        filterHolder.setInitParameter(CrossOriginFilter.ALLOWED_ORIGINS_PARAM, "*");
        filterHolder.setInitParameter(CrossOriginFilter.ALLOWED_HEADERS_PARAM,
                "Content-Type, Authorization, X-Requested-With, Content-Length, Accept, Origin");
        filterHolder.setInitParameter(CrossOriginFilter.ALLOWED_METHODS_PARAM, "GET, PUT, POST, DELETE, OPTIONS");
        filterHolder.setInitParameter(CrossOriginFilter.ALLOW_CREDENTIALS_PARAM, "true");

        return servletCtx;
    }

}
