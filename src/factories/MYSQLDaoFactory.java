package factories;

import daos.Cliente.ClienteDao;
import daos.Cliente.ClienteDaoSQL;
import daos.Factura.FacturaDao;
import daos.Factura.FacturaDaoSQL;
import daos.Factura_Producto.Factura_ProductoDao;
import daos.Factura_Producto.Factura_ProductoDaoSQL;
import daos.Producto.ProductoDao;
import daos.Producto.ProductoDaoSQL;

public class MYSQLDaoFactory extends DAOFactory{

	public MYSQLDaoFactory() {
		
	}
	@Override
	public ClienteDao getClienteDao() {
		return new ClienteDaoSQL();
	}
	@Override
	public ProductoDao getProductoDao() {
		// TODO Auto-generated method stub
		return new ProductoDaoSQL();
	}
	@Override
	public FacturaDao getFacturaDao() {
		// TODO Auto-generated method stub
		return new FacturaDaoSQL();
	}
	@Override
	public Factura_ProductoDao getFacturaProductoDao() {
		// TODO Auto-generated method stub
		return new Factura_ProductoDaoSQL();
	}
}
