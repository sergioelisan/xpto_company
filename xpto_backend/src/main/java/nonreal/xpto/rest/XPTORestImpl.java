package nonreal.xpto.rest;

import java.text.SimpleDateFormat;
import java.util.Map;

import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import nonreal.xpto.logic.RelatorioReceitaEmpresaPeriodo;
import nonreal.xpto.logic.RelatorioSaldoClientePeriodo;
import nonreal.xpto.logic.RelatorioSaldoTodosClientes;

/**
 * @author sergio lisan <sergiolisan@gmail.com>
 */
@Path("/")
public class XPTORestImpl implements XPTORest {

    @Override
    public Response echo() {
        return Response.ok("ECHO RESPONDIDO", MediaType.TEXT_PLAIN).build();
    }

    @Override
    public Response clientes(String body) {
        try {
            Map<String, String> request = new Gson().fromJson(body, new TypeToken<Map<String, String>>() {
            }.getType());

            var parser = new SimpleDateFormat("YYYY-MM-DD");

            var pessoaID = Integer.parseInt(request.get("pessoaID"));
            var inicio = parser.parse(request.get("inicio"));
            var fim = parser.parse(request.get("fim"));

            var result = new RelatorioSaldoClientePeriodo().printRelatorio(pessoaID, inicio, fim);

            return Response.ok(result, MediaType.TEXT_PLAIN).build();

        } catch (Exception e) {
            return Response.status(400, MediaType.TEXT_PLAIN).build();
        }
    }

    @Override
    public Response todosclientes(String body) {
        try {
            Map<String, String> request = new Gson().fromJson(body, new TypeToken<Map<String, String>>() {
            }.getType());

            var parser = new SimpleDateFormat("YYYY-MM-DD");

            var inicio = parser.parse(request.get("inicio"));
            var fim = parser.parse(request.get("fim"));

            var result = new RelatorioSaldoTodosClientes().printRelatorio(inicio, fim);

            return Response.ok(result, MediaType.TEXT_PLAIN).build();

        } catch (Exception e) {
            return Response.status(400, MediaType.TEXT_PLAIN).build();
        }
    }

    @Override
    public Response receitaempresa(String body) {
        try {
            Map<String, String> request = new Gson().fromJson(body, new TypeToken<Map<String, String>>() {
            }.getType());

            var parser = new SimpleDateFormat("YYYY-MM-DD");

            var inicio = parser.parse(request.get("inicio"));
            var fim = parser.parse(request.get("fim"));

            var result = new RelatorioReceitaEmpresaPeriodo().printRelatorio(inicio, fim);

            return Response.ok(result, MediaType.TEXT_PLAIN).build();

        } catch (Exception e) {
            return Response.status(400, MediaType.TEXT_PLAIN).build();
        }
    }

}
