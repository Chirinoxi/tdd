package cl.ucn.disc.pdbp.tdd.dao;

import cl.ucn.disc.pdbp.tdd.model.Control;

import java.util.List;
import java.util.Optional;

public class ControlDao implements Dao<Control>{


    @Override
    public Optional<Control> get(long id) {
        return Optional.empty();
    }

    @Override
    public List<Control> getAll() {
        return null;
    }

    @Override
    public void save(Control control) {

    }

    @Override
    public void update(Control control, String[] params) {

    }

    @Override
    public void delete(Control control) {

    }
}
