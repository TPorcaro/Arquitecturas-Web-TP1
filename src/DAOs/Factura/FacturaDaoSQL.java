package daos.Factura;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import pojos.Cliente;
import pojos.Factura;

public class FacturaDaoSQL implements FacturaDao {
	
	String driver;
	String uri;
	
	
	public FacturaDaoSQL() {
		this.driver = "com.mysql.cj.jdbc.Drive";
		this.uri = "jdbc:mysql://localhost:3306/example";
	}
	
	/**
	 * Crea la conexión con la base de datos
	 * @return Connection
	 */
	private Connection createConnection() {
		Connection conn;
		try {
			conn = DriverManager.getConnection(uri, "root", "");
			conn.setAutoCommit(false);
			return conn;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * Cierra la conexión que le pasamos por parametros
	 * @param conn 
	 * @return boolean que representa si se cerro la coneccion o no
	 */
	private boolean closeConnection(Connection conn) {
		try {
			conn.close();
			return conn.isClosed();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Dada una Factura, crea en la base de datos en la tabla factura,
	 * un registro con sus datos.
	 */
	@Override
	public void create(Factura pojo) throws SQLException {
		Connection conn = this.createConnection();
		String insert = "INSERT INTO factura (idFactura,idCliente) VALUES (?,?)";
		PreparedStatement ps = conn.prepareStatement(insert);
		ps.setInt(1, pojo.getIdFactura());
		ps.setInt(2, pojo.getIdCliente());
		ps.executeUpdate();
		ps.close();
		conn.commit();
		this.closeConnection(conn);
	}

	/**
	 * Dado un id que debera coincidir con una primary key
	 * en la tabla, elimina una Factura.
	 */
	@Override
	public boolean delete(int id) throws SQLException {
		Connection conn = this.createConnection();
		String delete = "DELETE FROM factura WHERE idFactura=?";
		PreparedStatement ps = conn.prepareStatement(delete);
		ps.setInt(1, id);
		int deleted = ps.executeUpdate();
		ps.close();
		conn.commit();
		this.closeConnection(conn);
		return deleted != 0;
	}

	/**
	 * Dado un id que debera coincidir con una primary key
	 * en la base de datos, devolvera una Factura
	 */
	@Override
	public Factura get(int id) throws SQLException {
		Connection conn = this.createConnection();
		String get = "SELECT FROM factura WHERE idFactura=?";
		PreparedStatement ps = conn.prepareStatement(get);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery(get);
		ps.close();
		conn.commit();
		this.closeConnection(conn);
		Factura p;
		if(rs.next()) {
			p = new Factura(rs.getInt(1),rs.getInt(2));
			return p;
		}else {
			return null;
		}
	}

	@Override
	/**
	 * Retorna una lista de Factura, donde se encuentran todas las facturas
	 */
	public List<Factura> getAll() throws SQLException {
		Connection conn = this.createConnection();
		String getAll = "SELECT * FROM factura";
		PreparedStatement ps = conn.prepareStatement(getAll);
		ResultSet rs = ps.executeQuery();
		conn.commit();
		ArrayList<Factura> facturaList = new ArrayList<Factura>();
		while(rs.next()) {
			Factura p = new Factura(rs.getInt(1),rs.getInt(2));
			facturaList.add(p);
		}
		ps.close();
		this.closeConnection(conn);
		return facturaList;
	}
	

}
