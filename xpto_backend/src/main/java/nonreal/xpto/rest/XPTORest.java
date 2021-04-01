package nonreal.xpto.rest;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @author sergio lisan <sergiolisan@gmail.com>
 */
public interface XPTORest {

    String XPTO_RELATORIO_CLIENTE = "/cliente";

    String XPTO_RELATORIO_TODOS_CLIENTES = "/clientes/todos";

    String XPTO_RELATORIO_RECEITA_EMPRESA = "/receita";

    /**
     * Hello from the server siiiiiiide!
     */
    @GET
    @Path("/echo")
    @Produces({ MediaType.TEXT_PLAIN })
    Response echo();

    /** */
    @POST
    @Path(XPTO_RELATORIO_CLIENTE)
    @Produces({ MediaType.TEXT_PLAIN })
    Response clientes(String body);

    /** */
    @POST
    @Path(XPTO_RELATORIO_TODOS_CLIENTES)
    @Produces({ MediaType.TEXT_PLAIN })
    Response todosclientes(String body);

    /** */
    @POST
    @Path(XPTO_RELATORIO_RECEITA_EMPRESA)
    @Produces({ MediaType.TEXT_PLAIN })
    Response receitaempresa(String body);

}
