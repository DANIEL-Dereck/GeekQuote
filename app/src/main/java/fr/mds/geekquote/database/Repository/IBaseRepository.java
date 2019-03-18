package fr.mds.geekquote.database.Repository;

import java.util.List;

public interface IBaseRepository<T> {
    int insert(T item);
    T update(T item);
    void delete(T item);
    void delete(int id);

    T get(int id);
    List<T> getAll();
}
