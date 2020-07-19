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

    /**
     * Convert all the persona's in the DB to json.
     * @param ctx
     */
    public static void getPersonas(Context ctx){

        List<Persona> personas = CONTRATOS.getAllPersonas();

        ctx.json(personas);

    }

    /**
     *  Insert a persona in the DB
     * @param ctx context
     */
    public static void insertarPersonas(Context ctx){

        String nombre = ctx.formParam("nombre");
        String apellido = ctx.formParam("nombre");
        String rutOk = ctx.formParam("rut");;
        String direccion = ctx.formParam("direccion");
        String email = ctx.formParam("email");
        Integer numFijo = Integer.parseInt(ctx.formParam("numeroFijo"));
        Integer numMovil = Integer.parseInt(ctx.formParam("numeroMovil"));

        Persona persona = new Persona(nombre, apellido, rutOk, direccion, numFijo, numMovil, email);

        CONTRATOS.registrarPersona(persona);

    }

    /**
     * Insert a ficha in the DB
     * @param ctx context
     */
    public static void insertarFicha(Context ctx){

        Long numeroFicha = Long.parseLong(ctx.formParam("numeroFicha"));
        String pacienteNombre = ctx.formParam("pacienteNombre");
        String especie = ctx.formParam("especie");
        String fechaNacimiento = ctx.formParam("fechaNacimiento");

        String sexoString = ctx.formParam("sexo");
        Sexo sexo = sexoString.equalsIgnoreCase("macho")? Sexo.MACHO: Sexo.HEMBRA;

        String tipoString =  ctx.formParam("tipo");
        Tipo tipo = tipoString.equalsIgnoreCase("interno")? Tipo.INTERNO: Tipo.EXTERNO;

        String raza = ctx.formParam("raza");
        String color = ctx.formParam("color");

        //if(sexoString.equalsIgnoreCase("macho")) sexo = Sexo.MACHO; else sexo = Sexo.HEMBRA;
        //if(tipoString.equalsIgnoreCase("interno")) tipo = Tipo.INTERNO; else tipo = Tipo.EXTERNO;

        Long personaId = Long.parseLong(ctx.formParam("personaId"));
        Persona persona = CONTRATOS.getPersonaById(personaId); // TODO: Consultar si la relación entre duenio y ficha se hace mediante ID o rut (Front-end).

        Ficha ficha = new Ficha(numeroFicha, pacienteNombre, especie, ZonedDateTime.now(), sexo, raza, color, tipo, persona);
        CONTRATOS.registrarPaciente(ficha);
    }

    /**
     * Returns all the 'controles' associated to a ficha object as JSON
     * @param ctx context
     */
    public static void getControlesFicha(Context ctx) {

        List<Ficha> fichaDB = CONTRATOS.buscarFicha(ctx.pathParam("numeroFicha"));

        List<Control> controles = fichaDB.get(0).getControles();

        ctx.json(controles);

    }

    /**
     * Returns JSON object with the data of a persona associated to a ficha.
     *
     * @param ctx Context
     */
    public static void getDuenio(Context ctx) {


        List<Ficha> fichaDB = CONTRATOS.buscarFicha(ctx.pathParam("numeroFicha"));

        //.debug("Los controles solicitados {}", fichaDB.get(0).getDuenio());
        Persona duenioDB = fichaDB.get(0).getDuenio();

        ctx.json(duenioDB);

    }
}
