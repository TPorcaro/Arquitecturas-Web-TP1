package daos;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 *  Esta interfaz se encarga de de definir los metodos 
 *  que tendran en comun los Daos
 */
public interface CrudDao<T> {
		void create(T pojo) throws SQLException;
		boolean delete(int id) throws SQLException;
		T get(int id) throws SQLException;
		List<T> getAll() throws SQLException;
}
