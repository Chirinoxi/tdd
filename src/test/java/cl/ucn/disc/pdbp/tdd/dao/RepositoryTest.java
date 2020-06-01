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

package cl.ucn.disc.pdbp.tdd.dao;

import cl.ucn.disc.pdbp.tdd.model.Persona;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


public final class RepositoryTest {


    @Test
    public void testRepository() {

        String databaseUrl = "jdbc:h2:mem:fivet_db";

        try(ConnectionSource connectionSource = new JdbcConnectionSource(databaseUrl)){

            // If the table 'persona' don't exists, we proceed to create it.
            TableUtils.createTableIfNotExists(connectionSource, Persona.class);

            Repository<Persona, Long> theRepo = new RepositoryOrmLite(connectionSource, Persona.class);

            List<Persona> personas = theRepo.findAll();
            Assertions.assertEquals(0, personas.size(), "Size != 0 !!!");

            Persona persona = new Persona("Ignacio","Chirino","19.445.801-0", "18 de Sept 449", 552373945, 953335379, "ignacio.chirino@alumnos.ucn.cl");
            Persona persona2 = new Persona("Bastihan","Chirino","20.212.289-2", "18 de Sept 449", 552373945, 953335379, "bcf1999@gmail.com");

            if(!theRepo.create(persona)) Assertions.fail("The repo create method doesn't works !!");


            if(!theRepo.create(persona2)) Assertions.fail("The repo create method doesn't works !!");


        } catch (SQLException | IOException exception ) {
            throw new RuntimeException(exception);
        }
    }

}
