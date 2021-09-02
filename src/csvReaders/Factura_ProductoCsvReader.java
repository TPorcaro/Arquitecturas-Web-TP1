package csvReaders;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import pojos.Cliente;
import pojos.Factura;
import pojos.Factura_Producto;

public class Factura_ProductoCsvReader implements CsvReaderInterface<Factura_Producto> {

	String file;
	public Factura_ProductoCsvReader (String file) {
		this.file = file;
	}
	@Override
	public ArrayList<Factura_Producto> readAndGetCsvFile() throws FileNotFoundException, IOException {
		@SuppressWarnings("deprecation")
		CSVParser parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader(this.file));
		ArrayList<Factura_Producto> facturaList = new ArrayList<Factura_Producto>();
		for(CSVRecord row: parser) {
			int idFactura = Integer.parseInt(row.get("idFactura"));
			int idProducto = Integer.parseInt(row.get("idProducto"));
			int cantidad = Integer.parseInt(row.get("cantidad"));
			Factura_Producto bill = new Factura_Producto(idFactura,idProducto,cantidad);
			facturaList.add(bill);
		}
		return facturaList;
	}

}
