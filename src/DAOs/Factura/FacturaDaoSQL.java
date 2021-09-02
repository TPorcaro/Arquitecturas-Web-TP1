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

	@Override
	public List<Factura> getAllByCliente(int cliente) throws SQLException {
		Connection conn = this.createConnection();
		String getAllByCliente = "";
		PreparedStatement ps = conn.prepareStatement(getAllByCliente);
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
