package cl.ucn.disc.pdbp.tdd;

import cl.ucn.disc.pdbp.tdd.dao.Repository;
import cl.ucn.disc.pdbp.tdd.dao.RepositoryOrmLite;
import cl.ucn.disc.pdbp.tdd.dao.StorageTest;
import cl.ucn.disc.pdbp.tdd.model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.*;

public final class ContratosImplTest {

    /**
     * The Logger.
     */
    private static final Logger log = LoggerFactory.getLogger(ContratosImplTest.class);

    private static final String databaseUrl = "jdbc:h2:mem:fivet_db";

    private static final ContratosImpl contratos = new ContratosImpl(databaseUrl);

    @Test
    public void testRegistrarPersona(){

        Persona persona = new Persona("Manuel", "Retamal", "13.642.156-5", "18 de Sept 449", 552246223,989015016 , "mretamal@hotmail.com");

        Persona personaDB = contratos.registrarPersona(persona);
        log.debug("Persona {}", personaDB);

        Assertions.assertEquals(personaDB.getId(), persona.getId(), "Los identificadores no son los mismos !!");

    }

    @Test
    public void testRegistrarPaciente(){


        Persona duenio = new Persona("Bastihan", "Chirino", "20.212.289-2", "18 de sept #449", 552246223, 994018727, "bcf1999@hotmail.com");
        Persona duenioDB = contratos.registrarPersona(duenio);

        Ficha ficha = new Ficha(1L, "Yaya", "Perro", ZonedDateTime.now(), Sexo.HEMBRA, "Labrador", "Cafe Claro", Tipo.EXTERNO, duenioDB);

        Ficha fichaDB = contratos.registrarPaciente(ficha);

        Assertions.assertEquals(ficha.getId(), fichaDB.getId(), "Los identificadores no son los mismos !!");

    }

    @Test
    public void testBuscarFicha(){

        Persona duenio = new Persona("Ingrid", "Farias", "14.112.424-2", "18 de sept #449", 552246223, 996585823, "ifc@hotmail.com");

        Persona duenio2 = new Persona("Ignacio", "Chirino", "19.445.801-0", "18 de sept #449", 552246223, 953335379, "bcf1999@hotmail.com");

        Ficha ficha = new Ficha(2L, "Nikita", "Perro", ZonedDateTime.now(), Sexo.HEMBRA, "Akita", "Cafe Claro", Tipo.EXTERNO, duenio);

        Ficha ficha2 = new Ficha(3L, "Sofia", "Pez", ZonedDateTime.now(), Sexo.HEMBRA, "Betta", "Gris", Tipo.EXTERNO, duenio);

        Ficha ficha3 = new Ficha(4L, "Baco", "Perro", ZonedDateTime.now(), Sexo.MACHO, "Pastor Aleman", "Negro", Tipo.INTERNO, duenio2);

        contratos.registrarPersona(duenio);
        contratos.registrarPersona(duenio2);

        contratos.registrarPaciente(ficha);
        contratos.registrarPaciente(ficha2);
        contratos.registrarPaciente(ficha3);

        List<Ficha> listaFichas = contratos.buscarFicha("14");

        log.debug("Lista de fichas: {}", listaFichas);

    }


}
