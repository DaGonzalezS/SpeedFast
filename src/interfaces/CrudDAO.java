package interfaces;

import java.sql.SQLException;
import java.util.List;

public interface CrudDAO<T> {

    int create(T entidad) throws SQLException;

    List<T> readAll() throws SQLException;

    boolean update(T entidad) throws SQLException;

    boolean delete(int id) throws SQLException;
}
