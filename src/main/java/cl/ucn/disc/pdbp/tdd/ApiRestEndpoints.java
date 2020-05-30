package cl.ucn.disc.pdbp.tdd;

import cl.ucn.disc.pdbp.tdd.model.Ficha;
import cl.ucn.disc.pdbp.tdd.model.Persona;
import io.javalin.http.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

/**
 * Api REST end points class.
 * @author ignac
 */
public final class ApiRestEndpoints {

    /**
     * The logger
     */
    private static final Logger log = LoggerFactory.getLogger(ApiRestEndpoints.class);

    /**
     * The contratos implementation wih SQLite
     */
    private static final Contratos CONTRATOS = new ContratosImpl("jdbc:sqlite:fivet.db");

    /**
     * Private constructor
     */
    private ApiRestEndpoints() {
        //Nothing
    }

    /**
     * @param ctx the Javalin {@link Context}.
     */
    public static void getAllFichas(Context ctx) {

        log.debug("Getting all the fichas.....");
        List<Ficha> fichas = CONTRATOS.getAllFichas();
        ctx.json(fichas);
    }

    /**
     * API Rest route for find fichas
     * @param ctx
     */
    public static void findFichas(Context ctx){

        String query = ctx.pathParam("query");
        log.debug("Finding fichas with query <{}> ...", query);

        List<Ficha> fichas = CONTRATOS.buscarFicha(query);
        ctx.json(fichas);

    }

    public static void getPersonas(Context ctx){

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

    }

}
