package cl.ucn.disc.pdbp.tdd.repository;

import cl.ucn.disc.pdbp.tdd.model.Persona;

import java.util.List;

public class PersonaRepository implements Repository<Persona, Long> {
    @Override
    public List<Persona> read() {
        return null;
    }

    @Override
    public Persona readById(Long id) {
        return null;
    }

    @Override
    public Persona create(Persona entity) {
        return null;
    }

    @Override
    public Persona update(Persona entity) {
        return null;
    }

    @Override
    public Persona delete(Persona entity) {
        return null;
    }
}
