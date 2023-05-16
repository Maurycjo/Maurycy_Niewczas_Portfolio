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


            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[i].length; j++) {
                    printWriter.print(matrix[i][j] + ",");
                }
                printWriter.println();
            }

            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public native double[][] loadAlgorithm(double[][] data, double[][] kernel);

    public static double[][] calculateSplot(double[][] data, double[][] kernel) {

        int size1 = data.length;
        int size2 = kernel.length;

        // Check if arrays have the same size
        if (size1 != size2) {
            throw new IllegalArgumentException("Array sizes do not match");
        }

        // Get row size
        int rowSize = data[0].length;

        // Create result array
        double[][] resultArray = new double[size1][rowSize];

        // Perform convolution for each row
        for (int i = 0; i < size1; i++) {
            double[] inputRow = data[i];
            double[] kernelRow = kernel[i];
            double[] resultRow = new double[rowSize];

            // Calculate convolution for row
            for (int j = 0; j < rowSize; j++) {
                resultRow[j] = 0.0;

                for (int k = 0; k < rowSize; k++) {
                    // Calculate convolution for element (i, j)
                    resultRow[j] += inputRow[k] * kernelRow[rowSize - k - 1];
                }
            }

            // Set row to result array
            resultArray[i] = resultRow;
        }

        return resultArray;
    }

    public static void main(String[] args) {


        double[][] data;
        double[][] kernel;
        double[][] result_native;
        double[][] result_java;
        try {
            data = loadData("/home/mniewczas/Desktop/java_lab/maunie814_javatz_2023/lab08/JNI/input_data/1.txt");
            kernel = loadData("/home/mniewczas/Desktop/java_lab/maunie814_javatz_2023/lab08/JNI/input_data/2.txt");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }



        result_java = calculateSplot(data, kernel);
        writeResultToFile("/home/mniewczas/Desktop/java_lab/maunie814_javatz_2023/lab08/JNI/input_data/result_java.txt", result_java);

        result_native = new JniAlgorithmLoader().loadAlgorithm(data, kernel);
        writeResultToFile("/home/mniewczas/Desktop/java_lab/maunie814_javatz_2023/lab08/JNI/input_data/result_native.txt", result_native);


    }




}