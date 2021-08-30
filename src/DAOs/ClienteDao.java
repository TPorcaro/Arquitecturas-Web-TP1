package DAOs;

import java.sql.SQLException;
import java.util.List;

import pojos.Cliente;

public interface ClienteDao extends CrudDao<Cliente>{
	
	List<Cliente> getClienteByFacturacion() throws SQLException;

}
