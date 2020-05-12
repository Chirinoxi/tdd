package cl.ucn.disc.pdbp.tdd.dao;
import cl.ucn.disc.pdbp.tdd.model.Ficha;

import java.util.List;
import java.util.Optional;

public class FichaDao implements Dao<Ficha>{
    @Override
    public Optional<Ficha> get(long id) {
        return Optional.empty();
    }

    @Override
    public List<Ficha> getAll() {
        return null;
    }

    @Override
    public void save(Ficha ficha) {

    }

    @Override
    public void update(Ficha ficha, String[] params) {

    }

    @Override
    public void delete(Ficha ficha) {

    }
}
