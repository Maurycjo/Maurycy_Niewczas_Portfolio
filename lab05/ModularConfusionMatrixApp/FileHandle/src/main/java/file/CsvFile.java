package file;

import ex.api.DataSet;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public class CsvFile {



    private Path filePath;
    private DataSet dataSet = new DataSet();

    public DataSet getDataSet() {
        return dataSet;
    }

    public CsvFile(Path filePath) {
        this.filePath = filePath;
    }

    public void readFile(){

        try {
            List<String> linesList = Files.readAllLines(filePath);
            long rowCount = linesList.size();

            String[][] data = new String[(int)rowCount][];

            for (int i = 0; i < rowCount; i++) {
                String[] values = linesList.get(i).split(",");

                data[i] = values;
            }

            dataSet.setHeader(data[0]);
            dataSet.setData(Arrays.copyOfRange(data, 1, data.length));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveFile(String data[][]){
    dataSet.setData(data);


    }

}
