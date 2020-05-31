package cl.ucn.disc.pdbp.tdd;

import cl.ucn.disc.pdbp.tdd.model.Ficha;
import cl.ucn.disc.pdbp.tdd.model.Persona;
import cl.ucn.disc.pdbp.tdd.model.Control;

import java.util.List;

/**
 * Contratos del sistema.
 * @author Ignacio Chirino.
 */
@SuppressWarnings("InterfaceNeverImplemented")
public interface Contratos {

    /**
     * Contrato: C-01 Registrar los datos de un paciente.
     * @param ficha to save in the backend.
     * @return the {@link Ficha} saved in the backend.
     */
    Ficha registrarPaciente(Ficha ficha);

    /**
     * Contrato: C-02 Registrar los datos de una persona.
     * @param persona to save in the backend.
     * @return the {@link Persona} saved.
     */
    Persona registrarPersona(Persona persona);

    /**
     * Contrato: C-03 buscar una ficha.
     * @param query to filter.
     * @return the {@link List} of {@link Ficha}.
     */
    List<Ficha> buscarFicha(String query);


    /**
     * Contrato: C-?? buscar todas las fichas.
     * @return the {@link List} of all the fichas.
     */
    List<Ficha> getAllFichas();
}
