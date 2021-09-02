package daos.Factura_Producto;

import java.sql.SQLException;

import daos.CrudDao;
import pojos.Factura_Producto;

public interface Factura_ProductoDao extends CrudDao<Factura_Producto> {

	boolean delete(int id1,int id2) throws SQLException;
	Factura_Producto get(int id1,int id2) throws SQLException;
}
