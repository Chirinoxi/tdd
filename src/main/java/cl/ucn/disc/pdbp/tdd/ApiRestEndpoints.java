/*
 * MIT License
 *
 * Copyright (c) [2020] [Ignacio Chirino Farías]
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

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

        List<Ficha> fichaDB = CONTRATOS.buscarFicha(context.pathParam("numeroFicha"));

        List<Control> controles = fichaDB.get(0).getControles();

        context.json(controles);

    }

    /**
     * Returns JSON object with the data of a persona associated to a ficha.
     *
     * @param context
     */
    public static void getDuenio(Context context) {


        List<Ficha> fichaDB = CONTRATOS.buscarFicha(context.pathParam("numeroFicha"));

        //.debug("Los controles solicitados {}", fichaDB.get(0).getDuenio());
        Persona duenioDB = fichaDB.get(0).getDuenio();

        context.json(duenioDB);

    }
}
