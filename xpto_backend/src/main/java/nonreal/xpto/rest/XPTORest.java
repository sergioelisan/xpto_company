package nonreal.xpto.rest;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.jetty.server.Response;

/**
 * @author sergio lisan <sergiolisan@gmail.com>
 */
public interface XPTORest {

    String XPTO_BACKEND_BASE_URI = "/";

    String XPTO_RELATORIO_CLIENTE = "/clientes";

    String XPTO_RELATORIO_TODOS_CLIENTES = "/clientes/todos";

    String XPTO_RELATORIO_RECEITA_EMPRESA = "/receita";

    /** */
    @POST
    @Path(XPTO_RELATORIO_CLIENTE)
    @Produces({ MediaType.APPLICATION_JSON })
    Response clientes(String body);

    /** */
    @POST
    @Path(XPTO_RELATORIO_TODOS_CLIENTES)
    @Produces({ MediaType.APPLICATION_JSON })
    Response todosclientes(String body);

    /** */
    @POST
    @Path(XPTO_RELATORIO_RECEITA_EMPRESA)
    @Produces({ MediaType.APPLICATION_JSON })
    Response receitaempresa(String body);

}
