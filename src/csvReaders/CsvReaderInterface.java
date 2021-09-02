package csvReaders;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface CsvReaderInterface<T> {

	List<T> readAndGetCsvFile() throws FileNotFoundException, IOException;
}
