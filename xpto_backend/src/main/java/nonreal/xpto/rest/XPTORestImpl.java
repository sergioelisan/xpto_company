package nonreal.xpto.rest;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.reflect.TypeToken;

import org.eclipse.jetty.server.Response;

import nonreal.xpto.logic.RelatorioSaldoClientePeriodo;

/**
 * @author sergio lisan <sergiolisan@gmail.com>
 */
@Path("/")
public class XPTORestImpl implements XPTORest {

    @Override
    public Response clientes(String body) {
        try {
            Map<String, String> request = createGson().fromJson(body, new TypeToken<Map<String, String>>() {
            }.getType());

            var parser = new SimpleDateFormat("YYYY-MM-DD");

            var pessoaID = request.get("pessoaID");
            var inicio = parser.parse(request.get("inicio"));
            var fim = parser.parse(request.get("fim"));

            var result = new RelatorioSaldoClientePeriodo().printRelatorio(pessoaID, inicio, fim);

            Sessions.INSTANCE.sendMessage(clientID, message);
            return Response.ok(zip(payload, zip), MediaType.APPLICATION_JSON).build();

        } catch (Exception e) {
            return Response.status(400, zip(JSONUtil.fromMapToJSON(result), zip)).build();
        }
    }

    @Override
    public Response todosclientes(String body) {
        var clientID = request.get("client-id");
        var message = request.get("message");

        var result = new ResponseMap();

        try {
            Sessions.INSTANCE.sendMessage(clientID, message);
            return Response.ok(zip(payload, zip), MediaType.APPLICATION_JSON).build();

        } catch (Exception e) {
            return Response.status(400, zip(JSONUtil.fromMapToJSON(result), zip)).build();
        }
    }

    @Override
    public Response receitaempresa(String body) {
        var clientID = request.get("client-id");
        var message = request.get("message");

        var result = new ResponseMap();

        try {
            Sessions.INSTANCE.sendMessage(clientID, message);
            return Response.ok(zip(payload, zip), MediaType.APPLICATION_JSON).build();

        } catch (Exception e) {
            return Response.status(400, zip(JSONUtil.fromMapToJSON(result), zip)).build();
        }
    }

    /** */
    private static Gson createGson() {
        var b = new GsonBuilder();
        b.registerTypeAdapter(Date.class, new DateDeserializer());
        b.registerTypeAdapter(Date.class, new DateSerializer());

        return b.create();
    }

    /**
     * desserializador de datas personalizado
     */
    private static class DateDeserializer implements JsonDeserializer<Date> {

        @Override
        public Date deserialize(JsonElement json, Type arg1, JsonDeserializationContext arg2)
                throws JsonParseException {
            return json == null ? null : new Date(json.getAsLong());
        }
    }

    /**
     * desserializador de datas personalizado
     */
    private static class DateSerializer implements JsonSerializer<Date> {

        @Override
        public JsonElement serialize(Date src, Type typeOfSrc, JsonSerializationContext context) {
            return src == null ? null : new JsonPrimitive(src.getTime());
        }
    }

}
