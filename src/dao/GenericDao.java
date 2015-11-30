package dao;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Alina on 02.11.2015.
 */
public interface GenericDao<K extends Serializable, T> {
    T findById(int id);
    List<T> findAll();
    boolean create (T value);
    boolean update (K value);
    boolean delete (K value);

}
