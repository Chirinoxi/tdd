package cl.ucn.disc.pdbp.tdd.model;

import static org.junit.jupiter.api.Assertions.*;

import cl.ucn.disc.pdbp.tdd.model.Persona;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Storage Test
 *
 * @author Ignacio Chirino Farías
 */
public final class StorageTest {

    /**
     * The Logger.
     */
    private static final Logger log = LoggerFactory.getLogger(StorageTest.class);

    /**
     * Testing ORMLite + H2 (Database)
     */
    @Test
    public void testDataBase(){

        // We configure the test database to use (in RAM memory)
        String databaseUrl = "jdbc:h2:mem:fivet_db";

        // Connection Source: autoclose with the try/catch
        try(ConnectionSource connectionSource = new JdbcConnectionSource(databaseUrl)){

            // Create the table from the Persona annotations.
            TableUtils.createTableIfNotExists(connectionSource, Persona.class);

            // The dao of Persona
            Dao<Persona, Long> daoPersona = DaoManager.createDao(connectionSource, Persona.class);

            // New Persona row
            Persona persona = new Persona("Bastihan", "Chirino", "20.212.289-2", "El director 5813", 953335379, 552373945, "bcf1999@hotmail.com");

            //Insert Persona into the database
            int tuples = daoPersona.create(persona);
            log.debug("Id {}", persona.getId());
            //

            Assertions.assertEquals(1, tuples, "Save tuples != 1");

            // Get from the DB
            Persona personaDB = daoPersona.queryForId(persona.getId());

            Assertions.assertEquals(persona.getNombre(), personaDB.getNombre(), "Nombres aren't equals !");
            Assertions.assertEquals(persona.getApellido(), personaDB.getApellido(), "Apellidos are not equals !");
            Assertions.assertEquals(persona.getRut(), personaDB.getRut(), "Rut's are not equals !");

            // Search by rut: SELECT * FROM 'persona' WHERE rut = '20.212.289-2'
            List<Persona> personaList = daoPersona.queryForEq("rut", "20.212.289-2");

            Assertions.assertEquals(1, personaList.size(), "Why there is more than one person ?");

            // Not found by rut
            Assertions.assertEquals(0, daoPersona.queryForEq("rut", "19").size(), "We found a wrong rut value in the database !!");

        }catch(IOException | SQLException e){

            log.error("Error !!", e);

        }

        // TODO: Implementar CRUD.

    }

}
