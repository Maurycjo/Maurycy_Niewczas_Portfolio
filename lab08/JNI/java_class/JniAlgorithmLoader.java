package java_class;

import java.io.*;

public class JniAlgorithmLoader{

    static{
        System.load("/home/mniewczas/Desktop/java_lab/maunie814_javatz_2023/lab08/JNI/library/libnativealg.so");
        //System.loadLibrary("nativealg");
    }

    public double[][] loadData(String fileName) throws IOException {

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

    public void writeResultToFile(String fileName, double[][] matrix){

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

    public double[][] calculateSplot(double[][] data, double[][] kernel) {

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

        JniAlgorithmLoader jniAlgorithmLoader = new JniAlgorithmLoader();

        double[][] data;
        double[][] kernel;
        double[][] result_native;
        double[][] result_java;


        long nativeTimeSum = 0;
        long normalTimeSum = 0;
        long startTime;
        long estimatedTime;

        


            try {
                data = jniAlgorithmLoader.loadData("/home/mniewczas/Desktop/java_lab/maunie814_javatz_2023/lab08/JNI/input_data/1.txt");
                kernel = jniAlgorithmLoader.loadData("/home/mniewczas/Desktop/java_lab/maunie814_javatz_2023/lab08/JNI/input_data/2.txt");

            } catch (IOException e) {
                throw new RuntimeException(e);
            }


            startTime = System.currentTimeMillis();
            result_java = jniAlgorithmLoader.calculateSplot(data, kernel);
            estimatedTime = System.currentTimeMillis() - startTime;
            System.out.println(estimatedTime);
            normalTimeSum +=estimatedTime;
            jniAlgorithmLoader.writeResultToFile("result_java.txt", result_java);

        

        System.out.println("****");

        


            try {
                data = jniAlgorithmLoader.loadData("/home/mniewczas/Desktop/java_lab/maunie814_javatz_2023/lab08/JNI/input_data/1.txt");
                kernel = jniAlgorithmLoader.loadData("/home/mniewczas/Desktop/java_lab/maunie814_javatz_2023/lab08/JNI/input_data/2.txt");

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            startTime = System.currentTimeMillis();
            result_native = jniAlgorithmLoader.loadAlgorithm(data, kernel);
            estimatedTime = System.currentTimeMillis() - startTime;
            System.out.println(estimatedTime);
            nativeTimeSum +=estimatedTime;
            jniAlgorithmLoader.writeResultToFile("result_native.txt", result_native);
        

        System.out.println("Java algorithm: " + normalTimeSum);
        System.out.println("C++  algorithm: " + nativeTimeSum);

    }




}