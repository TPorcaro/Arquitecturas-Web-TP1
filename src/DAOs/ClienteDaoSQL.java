package DAOs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pojos.Cliente;

public class ClienteDaoSQL implements ClienteDao {
	String driver;
	String uri;

	public ClienteDaoSQL() {
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
	public void create(Cliente pojo) throws SQLException {
		Connection conn = this.createConnection();
		String insert = "INSERT INTO cliente (idCliente, nombre, email) VALUES (?,?,?)";
		PreparedStatement ps = conn.prepareStatement(insert);
		ps.setInt(1, pojo.getIdCliente());
		ps.setString(2, pojo.getNombre());
		ps.setString(3,pojo.getEmail());
		ps.executeUpdate();
		ps.close();
		conn.commit();
		this.closeConnection(conn);
	}

	@Override
	public boolean delete(int id) throws SQLException {
		Connection conn = this.createConnection();
		String delete = "DELETE FROM cliente WHERE id=?";
		PreparedStatement ps = conn.prepareStatement(delete);
		ps.setInt(1, id);
		int deleted = ps.executeUpdate();
		ps.close();
		conn.commit();
		this.closeConnection(conn);
		return deleted != 0;
	}

	@Override
	public Cliente get(int id) throws SQLException {
		Connection conn = this.createConnection();
		String get = "SELECT FROM persona WHERE id=?";
		PreparedStatement ps = conn.prepareStatement(get);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery(get);
		ps.close();
		conn.commit();
		this.closeConnection(conn);
		Cliente p;
		if(rs.next()) {
			p = new Cliente(rs.getInt(1),rs.getString(2),rs.getString(3));
			return p;
		}else {
			return null;
		}
	}

	@Override
	public List<Cliente> getAll() throws SQLException {
		Connection conn = this.createConnection();
		String getAll = "SELECT * FROM cliente";
		PreparedStatement ps = conn.prepareStatement(getAll);
		ResultSet rs = ps.executeQuery();
		conn.commit();
		ArrayList<Cliente> clienteList = new ArrayList<Cliente>();
		while(rs.next()) {
			Cliente p = new Cliente(rs.getInt(1),rs.getString(2),rs.getString(3));
			clienteList.add(p);
		}
		ps.close();
		this.closeConnection(conn);
		return clienteList;
	}

	@Override
	public List<Cliente> getClienteByFacturacion() throws SQLException {
		Connection conn = this.createConnection();
		String getByFacturacion = "";
		PreparedStatement ps = conn.prepareStatement(getByFacturacion);
		ResultSet rs = ps.executeQuery();
		conn.commit();
		ArrayList<Cliente> clienteList = new ArrayList<Cliente>();
		while(rs.next()) {
			Cliente p = new Cliente(rs.getInt(1),rs.getString(2),rs.getString(3));
			clienteList.add(p);
		}
		ps.close();
		this.closeConnection(conn);
		return clienteList;
	}
	
	

}
