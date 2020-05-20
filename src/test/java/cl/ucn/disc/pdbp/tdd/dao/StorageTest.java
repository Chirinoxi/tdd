package cl.ucn.disc.pdbp.tdd.dao;

import cl.ucn.disc.pdbp.tdd.model.Ficha;
import cl.ucn.disc.pdbp.tdd.model.Persona;
import cl.ucn.disc.pdbp.tdd.model.Sexo;
import cl.ucn.disc.pdbp.tdd.model.Tipo;
import cl.ucn.disc.pdbp.tdd.model.Control;
import cl.ucn.disc.pdbp.tdd.utils.Entity;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.ArrayList;

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
            Persona persona = new Persona("Manuel", "Retamal", "13.642.156-5", "18 de Sept 449", 552246223,989015016 , "mretamal@hotmail.com");

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

            // Search by rut: SELECT * FROM 'persona' WHERE rut = '13.642.156-5'
            List<Persona> personaList = daoPersona.queryForEq("rut", "13.642.156-5");

            Assertions.assertEquals(1, personaList.size(), "Why there is more than one person ?");

            // Not found by rut
            Assertions.assertEquals(0, daoPersona.queryForEq("rut", "19").size(), "We found a wrong rut value in the database !!");

        }catch(IOException | SQLException e){

            log.error("Error !!", e);

        }

    }

    @Test
    public void testRepositoryFicha(){

        // We configure the test database to use (in RAM memory)
        String databaseUrl = "jdbc:h2:mem:fivet_db";

        // Connection Source: autoclose with the try/catch
        try(ConnectionSource connectionSource = new JdbcConnectionSource(databaseUrl)){

            // Create the table from the Persona annotations.
            TableUtils.createTableIfNotExists(connectionSource, Ficha.class);
            TableUtils.createTableIfNotExists(connectionSource, Persona.class);


            // 1. We create a persona from a Repository.
            Persona persona = new Persona("Bastihan", "Chirino", "20.212.289-2", "18 de sept #449", 552246223, 994018727, "bcf1999@hotmail.com");

            Repository<Persona, Long> personaRepo = new RepositoryOrmLite(connectionSource, Persona.class);

            if(!personaRepo.create(persona)){

                Assertions.fail("Cannot create persona !!");
            }

            //2. Instanciar una Ficha
            Ficha ficha = new Ficha("PA-001",
                    "Yaya",
                    "Perro",
                    ZonedDateTime.now(),
                    Sexo.HEMBRA,
                    "Labrador",
                    "Cafe Claro",
                    Tipo.EXTERNO,
                    persona );


            //3. Creamos repositorio para ficha e insertamos en la BD.
            Repository<Ficha,Long> fichaRepo = new RepositoryOrmLite(connectionSource, Ficha.class);

            if(!fichaRepo.create(ficha)){ Assertions.fail("We cannot create ficha !!"); }

            //4.- Obtener una ficha y revisar si sus atributos son distinto de null.

            Ficha fichaDB = fichaRepo.findById(1L);

            //Entity entity = new Entity();

            //String data = entity.toString(fichaDB);

            //System.out.println(data);

            Assertions.assertEquals(ficha.getPacienteNombre(), fichaDB.getPacienteNombre(), "(Ficha): Nombres aren't equals !");
            Assertions.assertEquals(ficha.getRaza(), fichaDB.getRaza(), " (Ficha): Raza's are not equals !");
            Assertions.assertEquals(ficha.getNumeroFicha(), fichaDB.getNumeroFicha(), "(Ficha): Numero de Ficha are not equals !");

            log.debug("Id {}", ficha.getId());

            // We test the update DAO method, specifically we update de owner of the pet.

            Persona persona2 = new Persona("Ignacio", "Chirino", "19.445.801-0", "18 de Sept #449", 552246223, 953335379, "ichirino@gmail.com");

            // Creamos la persona desde el repositorio, si no lo hacemos de este modo el atributo ID del objeto persona2 sera null !!!

            boolean createPersona2 = personaRepo.create(persona2);

            if(!createPersona2){

                Assertions.     fail("Cannot create persona 2!!");
            }

            fichaDB.setDuenio(persona2);

            fichaRepo.update(fichaDB);

            Ficha fichaDB2 = fichaRepo.findById(1L);

            // We define that 2 persons are 'equals' when they have the same rut.
            Assertions.assertEquals(persona2.getRut(), fichaDB2.getDuenio().getRut(), "(Ficha): The updated value it is not correct !!");

            // We test the delete method of the DAO
            fichaRepo.delete(ficha.getId());
            Assertions.assertEquals(fichaRepo.findById(ficha.getId()), null, "(Ficha): ID's aren't equals !");


        }catch(IOException | SQLException e){

            log.error("Error !!", e);

        }
    }

    @Test
    public void testRepositoryControl(){

        // We configure the test database to use (in RAM memory)
        String databaseUrl = "jdbc:h2:mem:fivet_db";

        // Connection Source: autoclose with the try/catch
        try(ConnectionSource connectionSource = new JdbcConnectionSource(databaseUrl)){

            // Create the table from the Persona annotations.
            TableUtils.createTableIfNotExists(connectionSource, Persona.class);
            TableUtils.createTableIfNotExists(connectionSource, Ficha.class);
            TableUtils.createTableIfNotExists(connectionSource, Control.class);

            Repository<Ficha, Long> fichaRepo = new RepositoryOrmLite(connectionSource, Ficha.class);
            Repository<Persona, Long> personaRepo = new RepositoryOrmLite(connectionSource, Persona.class);
            Repository<Control, Long> controlRepo = new RepositoryOrmLite(connectionSource, Control.class);

            // 1. We create a persona from a Repository.
            Persona persona = new Persona("Bastihan", "Chirino", "20.212.289-2", "18 de sept #449", 552246223, 994018727, "bcf1999@hotmail.com");
            Persona veterinario = new Persona("Manuel", "Retamal", "13.642.156-5", "18 de sept #449", 552246223, 989015016, "mretamal@iff.cl");

            if(!(personaRepo.create(persona) & personaRepo.create(veterinario))){
                Assertions.fail("Cannot create persona !!");
            }

            //2. Instanciar una Ficha
            Ficha ficha = new Ficha("PA-001",
                    "Yaya",
                    "Perro",
                    ZonedDateTime.now(),
                    Sexo.HEMBRA,
                    "Labrador",
                    "Cafe Claro",
                    Tipo.EXTERNO,
                    persona);

            //3. Creamos repositorio para ficha e insertamos en la BD.
            if(!fichaRepo.create(ficha)){ Assertions.fail("We cannot create ficha !!");}

            //4.- Obtener una ficha y revisar si sus atributos son distinto de null.
            Ficha fichaDB = fichaRepo.findById(1L);

            Assertions.assertEquals(ficha.getPacienteNombre(), fichaDB.getPacienteNombre(), "(Ficha): Nombres aren't equals !");
            Assertions.assertEquals(ficha.getRaza(), fichaDB.getRaza(), " (Ficha): Raza's are not equals !");
            Assertions.assertEquals(ficha.getNumeroFicha(), fichaDB.getNumeroFicha(), "(Ficha): Numero de Ficha are not equals !");

            Control control = new Control(ZonedDateTime.now(), ZonedDateTime.now().plusWeeks(1L), 39.0f, 20.0f, 1.2f, "Pukes", veterinario, fichaDB);

            if(!controlRepo.create(control)){
                Assertions.fail("We cannot create the control !!");
            }

            Entity entity = new Entity(); // We use this variable to print the data of any object.

            //fichaRepo.update(fichaDB);

            log.debug("Ficha: {}", entity.toString(fichaDB));

        }catch(IOException | SQLException e){
            log.error("Error !!", e);
        }


    }


}
