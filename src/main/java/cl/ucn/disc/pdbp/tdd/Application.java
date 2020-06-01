package cl.ucn.disc.pdbp.tdd;

import cl.ucn.disc.pdbp.tdd.model.Ficha;
import cl.ucn.disc.pdbp.tdd.model.Persona;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.javalin.Javalin;
import io.javalin.apibuilder.ApiBuilder;
import io.javalin.core.util.RouteOverviewPlugin;
import io.javalin.plugin.json.JavalinJson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * The main application
 * @author Ignacio Chirino Farías
 */
public final class Application {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    private Application(){
        //Nada
    }

    public static void main(String[] args){

        //Gson configuratin

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        JavalinJson.setFromJsonMapper(gson::fromJson);
        JavalinJson.setToJsonMapper(gson::toJson);

        Javalin javalin = Javalin.create(config -> {

            config.enableDevLogging();

            config.requestLogger(((ctx, executionTimeMs) -> {
                log.info("Served {} in {} ms.", ctx.fullUrl(), executionTimeMs);
                ctx.header("Server-Timing", "total;dur=" + executionTimeMs);
            }));

            // We enable the route's helper
            config.registerPlugin(new RouteOverviewPlugin("/routes"));

        }).routes(() -> {

            // Version v1
            ApiBuilder.path("v1", () -> {

                // Fichas
                ApiBuilder.path("fichas", () -> {

                    // C-01, Get -> /fichas
                    ApiBuilder.get(ApiRestEndpoints::getAllFichas);

                    // C-02, Get -> /fichas/find/{query}
                    ApiBuilder.path("find/:query", () -> {
                        ApiBuilder.get(ApiRestEndpoints::findFichas);
                    });

                });

                // Personas
                ApiBuilder.path("personas", () -> {
                    // C-03 Get -> /personas;
                    ApiBuilder.get(ApiRestEndpoints::getPersonas);

                    ApiBuilder.post(ApiRestEndpoints::getPersonas);
                });

            });

        }).start(7000);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            log.debug("Stopping the server....");
            javalin.stop();
            log.debug("Server was stopped !");
        }));
    }

}
