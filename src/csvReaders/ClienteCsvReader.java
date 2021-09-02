package csvReaders;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import pojos.Cliente;

public class ClienteCsvReader implements CsvReaderInterface<Cliente> {

	String file;
	public ClienteCsvReader (String file) {
		this.file = file;
	}
	@Override
	public ArrayList<Cliente> readAndGetCsvFile() throws FileNotFoundException, IOException {
		@SuppressWarnings("deprecation")
		CSVParser parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader(this.file));
		ArrayList<Cliente> clientList = new ArrayList<Cliente>();
		for(CSVRecord row: parser) {
			int id = Integer.parseInt(row.get("idCliente"));
			String nombre = row.get("nombre");
			String email = row.get("email");
			Cliente client = new Cliente(id,nombre,email);
			clientList.add(client);
		}
		return clientList;
	}

}
