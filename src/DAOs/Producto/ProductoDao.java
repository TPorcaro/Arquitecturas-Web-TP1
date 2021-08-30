package daos.Producto;

import java.sql.SQLException;

import daos.CrudDao;
import pojos.Producto;

public interface ProductoDao extends CrudDao<Producto>  {
	
	Producto getMasRecaudado() throws SQLException;

}
