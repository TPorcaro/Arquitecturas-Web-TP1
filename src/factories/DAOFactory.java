package factories;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import daos.Cliente.ClienteDao;
import daos.Factura.FacturaDao;
import daos.Factura_Producto.Factura_ProductoDao;
import daos.Producto.ProductoDao;

public abstract class DAOFactory {

	public static final int SQL = 1;
	public static final int DERBY = 2;
	
	
	public abstract ClienteDao getClienteDao();
	public abstract ProductoDao getProductoDao();
	public abstract FacturaDao getFacturaDao();
	public abstract Factura_ProductoDao getFacturaProductoDao();
	
	public static final String CLIENTETABLE = "CREATE TABLE cliente ("
						+ "idCliente int(11),"
						+ "nombre VARCHAR(500),"
						+ "email VARCHAR(150),"
						+ "PRIMARY KEY (idCLiente)"
						+ ")";
	public static final String FACTURATABLE = "CREATE TABLE factura ("
						+ "idFactura int(11),"
						+ "idCliente int(11),"
						+ "PRIMARY KEY (idFactura),"
						+ "FOREIGN KEY (idCliente) REFERENCES cliente(idCliente)"
						+ ")";
	public static final String PRODUCTOTABLE = "CREATE TABLE producto ("
						 + "idProducto int(11),"
						 + "nombre VARCHAR(45),"
						 + "valor FLOAT,"
						 + "PRIMARY KEY (idProducto)"
						 + ")";
	public static final String FACTURA_PRODUCTOTABLE = "CREATE TABLE factura_producto ("
								 + "idFactura int(11),"
								 + "idProducto int(11),"
								 + "cantidad int(11),"
								 + "PRIMARY KEY (idFactura,idProducto),"
								 + "FOREIGN KEY (idFactura) REFERENCES factura(idFactura),"
								 + "FOREIGN KEY (idProducto) REFERENCES producto(idProducto)"
								 + ")";

	private Connection createConnection() { // Solo es usado para la creacion de las tablas
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException | ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			System.exit(1);
		}
		Connection conn;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/example","root","");
			conn.setAutoCommit(false);
			return conn;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	private boolean closeConnection(Connection conn) { // Solo es usado para la creacion de las tablas
		try {
			conn.close();
			return conn.isClosed();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	public void createAllTables() throws SQLException {
		Connection conn = this.createConnection();
		conn.prepareStatement(DAOFactory.CLIENTETABLE).execute();
		conn.prepareStatement(DAOFactory.FACTURATABLE).execute();
		conn.prepareStatement(DAOFactory.PRODUCTOTABLE).execute();
		conn.prepareStatement(DAOFactory.FACTURA_PRODUCTOTABLE).execute();
		conn.commit();
		this.closeConnection(conn);
		
	}
	public static DAOFactory getDaoFactory(int factory) {
		
		switch(factory) {
		
		case SQL: return new MYSQLDaoFactory();
		default: return null;
		}
	}
}
