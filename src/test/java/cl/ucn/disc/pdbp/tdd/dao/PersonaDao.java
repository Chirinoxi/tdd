package cl.ucn.disc.pdbp.tdd.dao;
import cl.ucn.disc.pdbp.tdd.model.Persona;

import java.util.List;
import java.util.Optional;

public class PersonaDao implements Dao<Persona> {


    @Override
    public Optional<Persona> get(long id) {
        return Optional.empty();
    }

    @Override
    public List<Persona> getAll() {
        return null;
    }

    @Override
    public void save(Persona persona) {

    }

    @Override
    public void update(Persona persona, String[] params) {

    }

    @Override
    public void delete(Persona persona) {

    }
}
