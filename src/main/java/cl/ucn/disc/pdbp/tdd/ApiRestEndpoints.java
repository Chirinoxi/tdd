package cl.ucn.disc.pdbp.tdd;

import cl.ucn.disc.pdbp.tdd.model.*;
import io.javalin.http.Context;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.ZonedDateTime;
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
     * @param ctx Context
     */
    public static void findFichas(Context ctx){

        String query = ctx.pathParam("query");
        log.debug("Finding fichas with query <{}> ...", query);

        List<Ficha> fichas = CONTRATOS.buscarFicha(query);
        ctx.json(fichas);

    }

    public static void getPersonas(Context ctx){

        List<Persona> personas = CONTRATOS.getAllPersonas();

        ctx.json(personas);

    }

    public static void getControlesFicha(Context context) {


        Ficha fichaDB = CONTRATOS.getFichaById(Long.parseLong(context.pathParam("numeroFicha")));

        log.debug("Los controles solicitados {}", fichaDB.getControles());

        context.json(fichaDB.getControles());

    }
}
