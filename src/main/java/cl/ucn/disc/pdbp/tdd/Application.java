package cl.ucn.disc.pdbp.tdd;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.javalin.Javalin;
import io.javalin.core.util.RouteOverviewPlugin;
import io.javalin.plugin.json.JavalinJson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.ZonedDateTime;
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
    }

}
