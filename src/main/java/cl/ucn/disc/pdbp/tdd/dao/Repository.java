/*
 * MIT License
 *
 * Copyright (c) [2020] [Ignacio Chirino Farías]
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package cl.ucn.disc.pdbp.tdd.dao;

/**
 * Capa de acceso a los Datos
 * @author Ignacio Chirino
 */
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.List;

public interface Repository<T, K> {

    List<T> findAll();

    /**
     *
     * @param key atribute to apply the filter.
     * @param value to filter.
     * @return a list of an filtered objects.
     */
    List<T> findAll(String key, Object value);

    /**
     * This method allows us to construct different types of queries.
     * @return the {@link QueryBuilder}.
     */
    QueryBuilder<T, K> getQuery();

    /**
     * This method allows to find an object in the DB by an id parameter.
     * @param id to find.
     * @return the object with the id
     */
    T findById(K id);

    /**
     * This method creats an entity for insert into the DB.
     * @param entity to insert.
     * @return true or false.
     */
    boolean create(T entity);

    /**
     * This method allows us to update the fields of an entity in the DB (which should exist in the DB in the first place).
     * @param entity to update.
     * @return true or false, depending on whether we could insert or not.
     * @throws SQLException
     */
    boolean update(T entity) throws SQLException;

    /**
     * This method allows us to delete an entity on the DB, depending if exists an entity with the ID provided.
     * @param id to find.
     * @return true or false, deending the case.
     */
    boolean delete(K id);
}
