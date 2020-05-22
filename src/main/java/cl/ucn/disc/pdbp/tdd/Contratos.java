package cl.ucn.disc.pdbp.tdd;


import cl.ucn.disc.pdbp.tdd.model.Ficha;
import cl.ucn.disc.pdbp.tdd.model.Persona;

import java.util.List;

/**
 * Contratos del sistema.
 * @ author Ignacio Chirino.
 */
public interface Contratos {

    /**
     *
     * @param ficha to save in the backend.
     * @return the {@link Ficha} saved.
     */
    Ficha registrarPaciente(Ficha ficha);

    /**
     *
     * @param persona to save in the backend.
     * @return the {@link Persona} saved.
     */
    Persona registrarPersona(Persona persona);

    /**
     *
     * @param trozo to filter.
     * @return a list of Strings.
     */
    List<Ficha> buscarFicha(String trozo);

}
