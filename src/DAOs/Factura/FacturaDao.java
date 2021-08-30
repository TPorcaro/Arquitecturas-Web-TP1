package daos.Factura;

import java.sql.SQLException;
import java.util.List;

import daos.CrudDao;
import pojos.Factura;

public interface FacturaDao extends CrudDao<Factura> {
	
	List<Factura> getAllByCliente(int cliente) throws SQLException;

}
