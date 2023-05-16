package java_class;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;

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


    public native double[][] loadAlgorithm(double[][] data, double[][] kernel);

    public double[][] calculateSplot(double[][] data, double[][] kernel){



        return null;
    }

    public static void main(String[] args) {


        try {
            double data[][] = loadData("/home/mniewczas/Desktop/java_lab/maunie814_javatz_2023/lab08/JNI/input_data/1.txt");
            double kernel[][] = loadData("/home/mniewczas/Desktop/java_lab/maunie814_javatz_2023/lab08/JNI/input_data/2.txt");


            for(int i =0;i<14;i++){

                for(int j=0;j<14;j++){

                    System.out.print(data[i][j]);
                }
                System.out.println();
            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }





    }


}