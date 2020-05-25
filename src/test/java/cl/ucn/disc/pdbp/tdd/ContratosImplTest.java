package cl.ucn.disc.pdbp.tdd;

import cl.ucn.disc.pdbp.tdd.model.Ficha;
import cl.ucn.disc.pdbp.tdd.model.Persona;
import cl.ucn.disc.pdbp.tdd.model.Sexo;
import cl.ucn.disc.pdbp.tdd.model.Tipo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

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

        Assertions.assertEquals(personaDB.getId(), persona.getId(), "The id's are not the same !!");
        Assertions.assertEquals(personaDB.getRut(), persona.getRut(), "The rut's are not the same !!");
        Assertions.assertEquals(personaDB.getNombreApellido(), persona.getNombreApellido(), "The full name's are not the same !!");
        Assertions.assertEquals(personaDB.getNombre(), persona.getNombre(), "The nombre's are not the same !!");
        Assertions.assertEquals(personaDB.getApellido(), persona.getApellido(), "The apellido's are not the same !!");
        Assertions.assertEquals(personaDB.getDireccion(), persona.getDireccion(), "The direcciones are not the same !!");
        Assertions.assertEquals(personaDB.getTelefonoFijo(), persona.getTelefonoFijo(), "The telefonos fijos are not the same !!");
        Assertions.assertEquals(personaDB.getTelefonoMovil(), persona.getTelefonoMovil(), "The telefonos moviles are not the same !!");


    }

    @Test
    public void testRegistrarPaciente(){


        Persona duenio = new Persona("Bastihan", "Chirino", "20.212.289-2", "18 de sept #449", 552246223, 994018727, "bcf1999@hotmail.com");
        Persona duenioDB = contratos.registrarPersona(duenio);

        ZoneId zoneIdCl = ZoneId.of("Chile/Continental");
        ZonedDateTime fechaActual = ZonedDateTime.of(ZonedDateTime.now().getYear(), ZonedDateTime.now().getMonthValue(), ZonedDateTime.now().getDayOfMonth(), ZonedDateTime.now().getHour(), ZonedDateTime.now().getMinute(), ZonedDateTime.now().getSecond(), ZonedDateTime.now().getNano(), zoneIdCl);

        log.debug("La fecha es {}", fechaActual);

        Ficha ficha = new Ficha(1L, "Yaya", "Perro", fechaActual, Sexo.HEMBRA, "Labrador", "Cafe Claro", Tipo.EXTERNO, duenioDB);

        Ficha fichaDB = contratos.registrarPaciente(ficha);

        Assertions.assertEquals(ficha.getId(), fichaDB.getId(), "Los identificadores no son los mismos !!");
        Assertions.assertEquals(ficha.getColor(), fichaDB.getColor(), "The color are not the same!!");
        Assertions.assertEquals(ficha.getEspecie(), fichaDB.getEspecie(), "The especies are not the same !!");
        //Assertions.assertEquals(ficha.getFechaNacimiento(), fichaDB.getFechaNacimiento(), "The fecha de nacimiento are not the same !!");
        Assertions.assertEquals(ficha.getNumeroFicha(), fichaDB.getNumeroFicha(), "The numero the ficha are note the same !!");
        Assertions.assertEquals(ficha.getPacienteNombre(), fichaDB.getPacienteNombre(), "The paciente nombre are not the same!!");
        Assertions.assertEquals(ficha.getRaza(), fichaDB.getRaza(), "The raza's nombre are not the same!!");
        Assertions.assertEquals(ficha.getSexo(), fichaDB.getSexo(), "The sexo's nombre are not the same!!");
        Assertions.assertEquals(ficha.getTipo(), fichaDB.getTipo(), "The tipo's nombre are not the same!!");

        Assertions.assertEquals(ficha.getDuenio().getRut(), fichaDB.getDuenio().getRut(), "The duenios are not the same !!");
        Assertions.assertEquals(ficha.getDuenio().getNombreApellido(), fichaDB.getDuenio().getNombreApellido(), "The duenios doesn't have the same name !!");
        Assertions.assertEquals(ficha.getDuenio().getEmail(), fichaDB.getDuenio().getEmail(), "The duenios doesn't have the same email address !!");


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

        Assertions.assertEquals(ficha.getDuenio(), duenio, "The duenio assigned to ficha its not the same that the duenio attribute");
        Assertions.assertEquals(ficha2.getDuenio(), duenio, "The duenio assigned to ficha2 its not the same that the duenio attribute");
        Assertions.assertEquals(ficha3.getDuenio(), duenio2, "The duenio assigned to ficha3 its not the same that the duenio attribute");

    }


}
