package csvReaders;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import pojos.Producto;

public class ProductoCsvReader implements CsvReaderInterface<Producto> {

	String file;
	public ProductoCsvReader (String file) {
		this.file = file;
	}
	@Override
	public ArrayList<Producto> readAndGetCsvFile() throws FileNotFoundException, IOException {
		@SuppressWarnings("deprecation")
		CSVParser parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader(this.file));
		ArrayList<Producto> productList = new ArrayList<Producto>();
		for(CSVRecord row: parser) {
			int id = Integer.parseInt(row.get("idProducto"));
			String nombre = row.get("nombre");
			Float valor = Float.parseFloat(row.get("valor"));
			Producto bill = new Producto(id,nombre,valor);
			productList.add(bill);
		}
		return productList;
	}
}
