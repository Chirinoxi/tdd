package cl.ucn.disc.pdbp.tdd;

import cl.ucn.disc.pdbp.tdd.model.Ficha;
import cl.ucn.disc.pdbp.tdd.model.Persona;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.javalin.Javalin;
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

        }).start(7000);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            log.debug("Stopping the server....");
            javalin.stop();
            log.debug("Server was stopped !");
        }));

        javalin.get("/", ctx -> {
            ctx.result("The Date: " + ZonedDateTime.now());
        });

        // Route persona's

        javalin.get("/personas/", ctx -> {

          List<Persona> personas = Arrays.asList(
                  new Persona("Ignacio", "Chirino", "19.445.801-0",
                          "18 de Sept #449", 552246223, 953335379,
                          "ichirino@gmail.com"),

                  new Persona("Bastihan", "Chirino", "20.212.289-2",
                          "18 de sept #449", 552246223, 994018727,
                          "bcf1999@hotmail.com")
          );
            // We send the list
            ctx.json(personas);
        });

    }

}
