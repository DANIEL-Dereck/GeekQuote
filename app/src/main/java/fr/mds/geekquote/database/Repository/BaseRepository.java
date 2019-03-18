package fr.mds.geekquote.database.Repository;

import java.util.List;

public abstract class BaseRepository<T>{
    protected T item;

    protected abstract int insert(T item);
    protected abstract void update(T item);
    protected abstract void delete(T item);
    protected abstract void delete(int id);
    protected abstract T get(int id);
    protected abstract List<T> getAll();

    private String getDateTime() {
//        SimpleDateFormat dateFormat = new SimpleDateFormat(
//                "yyyy-MM-dd", Locale.getDefault());
//        Date date = new Date();
//        return dateFormat.format(date);
        return "";
    }

}
