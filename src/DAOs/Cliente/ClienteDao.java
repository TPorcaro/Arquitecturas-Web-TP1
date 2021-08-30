package daos.Cliente;

import java.sql.SQLException;
import java.util.List;

import daos.CrudDao;
import pojos.Cliente;

public interface ClienteDao extends CrudDao<Cliente>{
	
	List<Cliente> getClienteByFacturacion() throws SQLException;

}
