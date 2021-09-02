package csvReaders;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import pojos.Factura;

public class FacturaCsvReader implements CsvReaderInterface<Factura> {

	String file;
	public FacturaCsvReader (String file) {
		this.file = file;
	}
	@Override
	public ArrayList<Factura> readAndGetCsvFile() throws FileNotFoundException, IOException {
		@SuppressWarnings("deprecation")
		CSVParser parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader(this.file));
		ArrayList<Factura> facturaList = new ArrayList<Factura>();
		for(CSVRecord row: parser) {
			int id = Integer.parseInt(row.get("idFactura"));
			int cliente = Integer.parseInt(row.get("idCliente"));
			Factura bill = new Factura(id,cliente);
			facturaList.add(bill);
		}
		return facturaList;
	}
}
