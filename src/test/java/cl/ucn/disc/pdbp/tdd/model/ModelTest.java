package cl.ucn.disc.pdbp.tdd.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Model test.
 */
public final class ModelTest {

    /**
     * The Logger (console)
     */
    private static final Logger log = LoggerFactory.getLogger(ModelTest.class);

    /**
     * Test the Persona.
     * - El nombre no puede ser null.
     * - El nombre debe tener al menos 2 letras.
     * - El apellido no puede ser null.
     * - El apellido debe tener al menos 3 letras.
     * - El rut debe ser valido.
     */
    @Test
    public void testPersona() {

        log.debug("Testing Persona ..");

        // The data!
        log.debug(".. valid ..");
        String nombre = "Andrea";
        String apellido = "Contreras";
        String nombreApellido = nombre + " " + apellido;
        String rutOk = "152532873";
        String rutError = "15253287K";
        String direccion = "Salvador Reyes 1272";
        String email = "acontreras@ucn.cl";
        Integer numFijo = 55373945;
        Integer numMovil = 953335378;

        // Test constructor and getters
        Persona persona = new Persona(nombre, apellido, rutOk, direccion, numFijo, numMovil, email);
        Assertions.assertEquals(persona.getNombre(), nombre);
        Assertions.assertEquals(persona.getApellido(), apellido);
        Assertions.assertEquals(persona.getNombreApellido(), nombreApellido);

        // Testing nullity
        log.debug(".. nullity ..");
        Assertions.assertThrows(NullPointerException.class, () -> new Persona(nombre, null, null, null, null, null, email));
        Assertions.assertThrows(NullPointerException.class, () -> new Persona(null, apellido, null, null, null, null, email));
        Assertions.assertThrows(NullPointerException.class, () -> new Persona(null, null, rutOk, null, null, null, email));
        Assertions.assertThrows(NullPointerException.class, () -> new Persona(nombre, apellido, null, null, null, null, email));
        Assertions.assertThrows(NullPointerException.class, () -> new Persona(nombre, null, rutOk, null, null, null, email));
        Assertions.assertThrows(NullPointerException.class, () -> new Persona(null, apellido, rutOk, null, null, null, email));
        Assertions.assertThrows(NullPointerException.class, () -> new Persona(null, null, null, null, null, null, email));

        // Testing invalid rut
        log.debug(".. rut ..");
        Assertions.assertThrows(RuntimeException.class, () -> new Persona(nombre, apellido, rutError, direccion, numFijo, numMovil, email));


        log.debug(".. verificando nombre y apellido ..");

        boolean nameSize = (nombre.length() >= 2);
        boolean lastNameSize = (apellido.length() >= 3);

        Assertions.assertTrue(nameSize);
        Assertions.assertTrue(lastNameSize);


        log.debug("Done.");
    }

    /**
     * Test the digito verificador.
     */
    @Test
    public void testDigitoVerificador() {

        Assertions.assertFalse(Validation.isRutValid(null));
        Assertions.assertTrue(Validation.isRutValid("152532873"));
        Assertions.assertTrue(Validation.isRutValid("21195194K"));
        Assertions.assertTrue(Validation.isRutValid("121244071"));
        Assertions.assertTrue(Validation.isRutValid("198127949"));
        Assertions.assertTrue(Validation.isRutValid("202294316"));

        Assertions.assertFalse(Validation.isRutValid("1525A2873"));
        Assertions.assertFalse(Validation.isRutValid("15253287K"));
        Assertions.assertFalse(Validation.isRutValid("15253287-"));

    }

    /**
     * Test for a valid email.
     */
    @Test
    public void testEmail(){
        Assertions.assertTrue(Validation.isEmailValid("ignacio.chirino@alumnos.ucn.cl"));
        Assertions.assertTrue(Validation.isEmailValid("ichirinofarias97@gmail.com"));
        Assertions.assertTrue(Validation.isEmailValid("jbekios@ucn.cl"));
        Assertions.assertTrue(Validation.isEmailValid("yhadfeg@inverisonesfarias.cl"));

        Assertions.assertFalse(Validation.isEmailValid("@alumnos.ucn.cl"));
        Assertions.assertTrue(Validation.isEmailValid("ichirino@alumnos"));

    }

}
