package java_class;


public class JniAlgorithmLoader{

    static{
        System.load("/home/mniewczas/Desktop/java_lab/maunie814_javatz_2023/lab08/JNI/library/libnativealg.so");
        //System.loadLibrary("lib_native_alg");
    }

    public native int[][] loadAlgorithm(int[][] data, int[][] kernel);

    public static void main(String[] args) {
        
        int result[][] = new int[10][10];
        int data[][] = new int[10][10];
        int kernel[][] = new int[10][10];


        result = new JniAlgorithmLoader().loadAlgorithm(data, kernel);

    }

}