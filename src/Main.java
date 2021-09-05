import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import csvReaders.ClienteCsvReader;
import csvReaders.FacturaCsvReader;
import csvReaders.Factura_ProductoCsvReader;
import csvReaders.ProductoCsvReader;
import daos.Cliente.ClienteDaoSQL;
import daos.Factura.FacturaDaoSQL;
import daos.Factura_Producto.Factura_ProductoDao;
import daos.Factura_Producto.Factura_ProductoDaoSQL;
import daos.Producto.ProductoDaoSQL;
import factories.DAOFactory;
import pojos.Cliente;
import pojos.Factura;
import pojos.Factura_Producto;
import pojos.Producto;

public class Main {

	public static void main(String[] args) throws FileNotFoundException, IOException, SQLException {
		DAOFactory factory = DAOFactory.getDaoFactory(DAOFactory.SQL);
		//factory.createAllTables();
		ClienteDaoSQL clienteDao = (ClienteDaoSQL) factory.getClienteDao();
		FacturaDaoSQL facturaDao = (FacturaDaoSQL) factory.getFacturaDao();
		ProductoDaoSQL productoDao = (ProductoDaoSQL) factory.getProductoDao();
		Factura_ProductoDao FacturaProductodao = (Factura_ProductoDaoSQL) factory.getFacturaProductoDao();
		ArrayList<Cliente> clientList = new ClienteCsvReader("./src/FilesCsv/clientes.csv").readAndGetCsvFile();
		ArrayList<Factura> facturaList = new FacturaCsvReader("./src/FilesCsv/facturas.csv").readAndGetCsvFile();
		ArrayList<Producto> productoList = new ProductoCsvReader("./src/FilesCsv/productos.csv").readAndGetCsvFile();
		ArrayList<Factura_Producto> facturaProdList = new Factura_ProductoCsvReader("./src/FilesCsv/facturas-productos.csv").readAndGetCsvFile();
		/**
		 * System.out.println("--------------------------");
		for(Cliente client: clientList) {
			clienteDao.create(client);
		}
		System.out.println("--------------------------");
		for(Factura bill: facturaList) {
			facturaDao.create(bill);
		}
		System.out.println("--------------------------");
		for(Producto product: productoList) {
			productoDao.create(product);
		}
		System.out.println("--------------------------");
		for(Factura_Producto billProd: facturaProdList) {
			FacturaProductodao.create(billProd);
		}
		*/
		System.out.println(productoDao.getMasRecaudado());
		ArrayList<Cliente> clientByFacturacion= (ArrayList<Cliente>) clienteDao.getClienteByFacturacion();
		for(Cliente client: clientByFacturacion) {
			System.out.println(client);
		}
		
		
	}

}
