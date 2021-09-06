package daos.Cliente;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pojos.Cliente;
/**
 * Esta clase implementa la interfaz de ClienteDao,
 * por lo tanto tambien implementa en metodos concretos
 * los metodos que definia ClienteDao.
 */
public class ClienteDaoSQL implements ClienteDao {
	String driver;
	String uri;

	public ClienteDaoSQL() {
		this.driver = "com.mysql.cj.jdbc.Drive";
		this.uri = "jdbc:mysql://localhost:3306/example";
	}
	/**
	 * Crea una coneccion a una base de datos SQL
	 * @return una conexion de tipo Connection de sql
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
	 * Se cierra una conexión dado una conexión dada
	 * @param conn
	 * @return boolean, que representa si se cerro la conexión o no
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
	 * Crea un cliente en la base de datos, dado un objeto Cliente.
	 */
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

	/**
	 * Borra un cliente dado un id, este coincidira con una primary key
	 * de la tabla cliente.
	 */
	@Override
	public boolean delete(int id) throws SQLException {
		Connection conn = this.createConnection();
		String delete = "DELETE FROM cliente WHERE idCliente=?";
		PreparedStatement ps = conn.prepareStatement(delete);
		ps.setInt(1, id);
		int deleted = ps.executeUpdate();
		ps.close();
		conn.commit();
		this.closeConnection(conn);
		return deleted != 0;
	}

	/**
	 * Retorna un Cliente dado un id, que debera coincidir con una primary key
	 * de la tabla cliente.
	 */
	@Override
	public Cliente get(int id) throws SQLException {
		Connection conn = this.createConnection();
		String get = "SELECT FROM cliente WHERE idCliente=?";
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

	/**
	 * Retorna todos los clientes que se encuentran
	 * en la tabla cliente
	 */
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

	/**
	 * Este metodo retorna una lista de cliente,
	 * ordenados por la cantidad de facturacion que posee cada uno,
	 * 
	 */
	@Override
	public List<Cliente> getClienteByFacturacion() throws SQLException {
		Connection conn = this.createConnection();
		String getByFacturacion = "SELECT c.idCliente, c.nombre, c.email, SUM(fp.cantidad) as cantidad, SUM(fp.cantidad)*p.valor AS total "
				+ "FROM cliente c "
				+ "JOIN factura f ON (c.idCliente = f.idCliente) "
				+ "JOIN factura_producto fp ON f.idFactura = fp.idFactura "
				+ "JOIN producto p ON fp.idProducto = p.idProducto GROUP BY c.idCliente ORDER BY total DESC";
		PreparedStatement ps = conn.prepareStatement(getByFacturacion);
		ResultSet rs = ps.executeQuery();
		conn.commit();
		ArrayList<Cliente> clienteList = new ArrayList<Cliente>();
		while(rs.next()) { // Se iteran las filas y se crea un cliente por fila, se agrega a una lista de Cliente.
			Cliente p = new Cliente(rs.getInt(1),rs.getString(2),rs.getString(3));
			clienteList.add(p);
		}
		ps.close();
		this.closeConnection(conn);
		return clienteList;
	}
	
	

}
