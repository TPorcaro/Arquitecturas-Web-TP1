package factories;

import daos.Cliente.ClienteDao;
import daos.Factura.FacturaDao;
import daos.Producto.ProductoDao;

public abstract class DAOFactory {

	public static final int SQL = 1;
	public static final int DERBY = 2;
	
	
	public abstract ClienteDao getClienteDao();
	public abstract ProductoDao getProductoDao();
	public abstract FacturaDao getFacturaDao();
	
	String clienteTable = "CREATE TABLE cliente ("
						+ "idCliente int(11),"
						+ "nombre VARCHAR(500),"
						+ "email VARCHAR(150),"
						+ "PRIMARY KEY (idCLiente)"
						+ ")";
	String facturaTable = "CREATE TABLE factura ("
						+ "idFactura int(11),"
						+ "idCliente int(11)"
						+ "PRIMARY KEY (idFactura),"
						+ "FOREIGN KEY (idCliente) REFERENCES cliente(idCliente)"
						+ ")";
	String ProductoTable = "CREATE TABLE producto ("
						 + "idProducto int(11),"
						 + "nombre VARCHAR(45),"
						 + "valor FLOAT,"
						 + "PRIMARY KEY (idProducto)"
						 + ")";
	String Factura_ProductoTable = "CREATE TABLE factura_producto ("
								 + "idFactura int(11),"
								 + "idProducto int(11),"
								 + "cantidad int(11),"
								 + "PRIMARY KEY (idFactura,idProducto),"
								 + "FOREIGN KEY (idFactura) REFERENCES factura(idFactura),"
								 + "FOREIGN KEY (idProducto) REFERENCES producto(idProducto)"
								 + ")";
	/*CREATE TABLE factura_producto (
			 idFactura int(11),
			 idProducto int(11),
			 cantidad int(11),
			 PRIMARY KEY (idFactura,idProducto),
			 FOREIGN KEY (idFactura) REFERENCES factura(idFactura),
			 FOREIGN KEY (idProducto) REFERENCES producto(idProducto)
			 );*/

	public static DAOFactory getDaoFactory(int factory) {
		
		switch(factory) {
		
		case SQL: return new MYSQLDaoFactory();
		default: return null;
		}
	}
}
