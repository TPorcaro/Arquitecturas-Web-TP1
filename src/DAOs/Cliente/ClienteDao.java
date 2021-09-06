package daos.Cliente;

import java.sql.SQLException;
import java.util.List;

import daos.CrudDao;
import pojos.Cliente;

/**
 * Esta interfaz extiende de la interfaz CrudDao,
 * por lo tanto ademas de los metodos que hereda,
 * tambien se define otro metodo propio de Cliente
 */
public interface ClienteDao extends CrudDao<Cliente>{
	/**
	 * @return una lista de Cliente
	 * @throws SQLException
	 */
	List<Cliente> getClienteByFacturacion() throws SQLException;

}
