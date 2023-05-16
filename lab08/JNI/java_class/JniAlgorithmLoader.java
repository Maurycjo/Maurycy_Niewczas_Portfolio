package java_class;

import java.io.*;

public class JniAlgorithmLoader{

    static{
        System.load("/home/mniewczas/Desktop/java_lab/maunie814_javatz_2023/lab08/JNI/library/libnativealg.so");
        //System.loadLibrary("lib_native_alg");
    }

    public static double[][] loadData(String fileName) throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader(fileName));

        int size = Integer.parseInt(reader.readLine());

        double[][] matrix = new double[size][size];

        String line;
        int row = 0;
        while ((line = reader.readLine()) != null) {
            String[] values = line.split(",");
            for (int col = 0; col < size; col++) {
                matrix[row][col] = Double.parseDouble(values[col]);
            }
            row++;
        }
        reader.close();
        return matrix;
    }

    public static void writeResultToFile(String fileName, double[][] matrix){

        try {
            FileWriter fileWriter = new FileWriter(fileName);
            PrintWriter printWriter = new PrintWriter(fileWriter);

            // Zapisywanie macierzy do pliku
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[i].length; j++) {
                    printWriter.print(matrix[i][j] + ",");
                }
                printWriter.println(); // Nowy wiersz po każdym wierszu macierzy
            }

            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    public native double[][] loadAlgorithm(double[][] data, double[][] kernel);

    public double[][] calculateSplot(double[][] data, double[][] kernel){

        return null;
    }

    public static void main(String[] args) {


        double[][] data;
        double[][] kernel;
        double[][] result;
        try {
            data = loadData("/home/mniewczas/Desktop/java_lab/maunie814_javatz_2023/lab08/JNI/input_data/1.txt");
            kernel = loadData("/home/mniewczas/Desktop/java_lab/maunie814_javatz_2023/lab08/JNI/input_data/2.txt");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        result = new JniAlgorithmLoader().loadAlgorithm(data, kernel);
        writeResultToFile("/home/mniewczas/Desktop/java_lab/maunie814_javatz_2023/lab08/JNI/input_data/result.txt", result);


    }




}