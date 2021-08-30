package DAOs;

import java.util.List;

import pojos.Factura;

public interface FacturaDao extends CrudDao<Factura> {
	
	List<Factura> getAllByCliente(int cliente);

}
