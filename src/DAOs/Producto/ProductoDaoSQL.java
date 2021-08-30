package daos.Producto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pojos.Cliente;
import pojos.Producto;

public class ProductoDaoSQL implements ProductoDao {
	String driver;
	String uri;

	public ProductoDaoSQL() {
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
	public void create(Producto pojo) throws SQLException {
		Connection conn = this.createConnection();
		String insert = "INSERT INTO producto (idProducto, nombre, valor) VALUES (?,?,?)";
		PreparedStatement ps = conn.prepareStatement(insert);
		ps.setInt(1, pojo.getIdProducto());
		ps.setString(2, pojo.getNombre());
		ps.setFloat(3, pojo.getValor());
		ps.executeUpdate();
		ps.close();
		conn.commit();
		this.closeConnection(conn);
	}

	@Override
	public boolean delete(int id) throws SQLException {
		Connection conn = this.createConnection();
		String delete = "DELETE FROM producto WHERE id=?";
		PreparedStatement ps = conn.prepareStatement(delete);
		ps.setInt(1, id);
		int deleted = ps.executeUpdate();
		ps.close();
		conn.commit();
		this.closeConnection(conn);
		return deleted != 0;
	}

	@Override
	public Producto get(int id) throws SQLException {
		Connection conn = this.createConnection();
		String get = "SELECT FROM cliente WHERE id=?";
		PreparedStatement ps = conn.prepareStatement(get);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery(get);
		ps.close();
		conn.commit();
		this.closeConnection(conn);
		Producto p;
		if(rs.next()) {
			p = new Producto(rs.getInt(1),rs.getString(2),rs.getFloat(3));
			return p;
		}else {
			return null;
		}
	}

	@Override
	public List<Producto> getAll() throws SQLException {
		Connection conn = this.createConnection();
		String getAll = "SELECT * FROM cliente";
		PreparedStatement ps = conn.prepareStatement(getAll);
		ResultSet rs = ps.executeQuery();
		conn.commit();
		ArrayList<Producto> productoList = new ArrayList<Producto>();
		while(rs.next()) {
			Producto p = new Producto(rs.getInt(1),rs.getString(2),rs.getFloat(3));
			productoList.add(p);
		}
		ps.close();
		this.closeConnection(conn);
		return productoList;
	}
	@Override
	public Producto getMasRecaudado() throws SQLException {
		Connection conn = this.createConnection();
		String getMasRecaudado = "SELECT blabla";
		PreparedStatement ps = conn.prepareStatement(getMasRecaudado);
		ResultSet rs = ps.executeQuery();
		conn.commit();
		ps.close();
		this.closeConnection(conn);
		while(rs.next()) {
			Producto p = new Producto(rs.getInt(1),rs.getString(2),rs.getFloat(3));
			return p;
		}
		return null;
	}

	
	

}
