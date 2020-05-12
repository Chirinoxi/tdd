package cl.ucn.disc.pdbp.tdd.repository;

import java.util.List;

public interface Repository<T, K> {

    List<T> read();

    T readById(K id);
    T create(T entity);
    T update(T entity);
    T delete(T entity);

}
