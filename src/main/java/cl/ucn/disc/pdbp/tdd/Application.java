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

        //Gson configuration

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

                    // C-01, GET -> /fichas
                    ApiBuilder.get(ApiRestEndpoints::getAllFichas);

                    // POST -> Insertar Ficha
                    ApiBuilder.path("create/*", () -> {

                        ApiBuilder.post(ApiRestEndpoints::insertarFicha);
                    });


                    // C-02
                    ApiBuilder.path("find/:query", () -> {
                        //GET -> /fichas/find/{query}
                        ApiBuilder.get(ApiRestEndpoints::findFichas);
                    });

                    // C-05
                    ApiBuilder.path(":numeroFicha/controles", () -> {

                        //GET -> /fichas/{numeroFichas}/controles
                        ApiBuilder.get(ApiRestEndpoints::getControlesFicha);

                        //POST -> Agregar un control con un numero de ficha dado.
                        ApiBuilder.post(ApiRestEndpoints::insertarFicha);

                    });

                    // C-06
                    ApiBuilder.path(":numeroFicha/duenio", () -> {
                        //  GET -> fichas/{numeroFicha}/controles
                        ApiBuilder.get(ApiRestEndpoints::getDuenio);
                    });

                });
                // Personas
                ApiBuilder.path("personas", () -> {
                    // C-03 GET -> /personas;
                    ApiBuilder.get(ApiRestEndpoints::getPersonas);

                    ApiBuilder.post(ApiRestEndpoints::insertarPersonas);
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
