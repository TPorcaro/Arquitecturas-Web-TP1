package csvReaders;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 * Esta interfaz se encarga de definir el metodo que se encargara de leer y retornar
 * una lista de objetos
 * @author Usuario
 *
 * @param <T>
 */
public interface CsvReaderInterface<T> {

	List<T> readAndGetCsvFile() throws FileNotFoundException, IOException;
}
