package daos.Factura_Producto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import pojos.Cliente;
import pojos.Factura;
import pojos.Factura_Producto;

public class Factura_ProductoDaoSQL implements Factura_ProductoDao {
	
	String driver;
	String uri;
	
	
	public Factura_ProductoDaoSQL() {
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
	public void create(Factura_Producto pojo) throws SQLException {
		Connection conn = this.createConnection();
		String insert = "INSERT INTO factura_producto (idFactura,idProducto,cantidad) VALUES (?,?,?)";
		PreparedStatement ps = conn.prepareStatement(insert);
		ps.setInt(1, pojo.getIdFactura());
		ps.setInt(2, pojo.getIdProducto());
		ps.setInt(3, pojo.getCantidad());
		ps.executeUpdate();
		ps.close();
		conn.commit();
		this.closeConnection(conn);
	}

	@Override
	public boolean delete(int idFactura,int idProducto) throws SQLException {
		Connection conn = this.createConnection();
		String delete = "DELETE FROM factura_producto WHERE idFactura=? AND idProducto=?";
		PreparedStatement ps = conn.prepareStatement(delete);
		ps.setInt(1, idFactura);
		ps.setInt(2, idProducto);
		int deleted = ps.executeUpdate();
		ps.close();
		conn.commit();
		this.closeConnection(conn);
		return deleted != 0;
	}

	@Override
	public Factura_Producto get(int idFactura,int idProducto) throws SQLException {
		Connection conn = this.createConnection();
		String get = "SELECT FROM factura_producto WHERE idFactura=? AND idProducto=?";
		PreparedStatement ps = conn.prepareStatement(get);
		ps.setInt(1, idFactura);
		ps.setInt(2, idProducto);
		ResultSet rs = ps.executeQuery(get);
		ps.close();
		conn.commit();
		this.closeConnection(conn);
		Factura_Producto p;
		if(rs.next()) {
			p = new Factura_Producto(rs.getInt(1),rs.getInt(2),rs.getInt(3));
			return p;
		}else {
			return null;
		}
	}

	@Override
	public List<Factura_Producto> getAll() throws SQLException {
		Connection conn = this.createConnection();
		String getAll = "SELECT * FROM factura_producto";
		PreparedStatement ps = conn.prepareStatement(getAll);
		ResultSet rs = ps.executeQuery();
		conn.commit();
		ArrayList<Factura_Producto> facturaProductoList = new ArrayList<Factura_Producto>();
		while(rs.next()) {
			Factura_Producto p = new Factura_Producto(rs.getInt(1),rs.getInt(2),rs.getInt(3));
			facturaProductoList.add(p);
		}
		ps.close();
		this.closeConnection(conn);
		return facturaProductoList;
	}

	@Override
	public boolean delete(int id) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Factura_Producto get(int id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	

}

