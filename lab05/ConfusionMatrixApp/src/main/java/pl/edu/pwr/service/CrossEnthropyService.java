package pl.edu.pwr.service;

import pl.edu.pwr.ex.api.AnalysisException;
import pl.edu.pwr.ex.api.AnalysisService;
import pl.edu.pwr.ex.api.DataSet;

public class CrossEnthropyService implements AnalysisService {

    private double result;

    @Override
    public void setOptions(String[] options) throws AnalysisException {

    }

    @Override
    public String getName() {
        return "Cross Enthropy";
    }

    @Override
    public void submit(DataSet ds) throws AnalysisException {

        String[][] matrix = ds.getData();

        int n = matrix.length;
        double crossEntropy = 0.0;

        for (int i = 0; i < n; i++) {
            double rowSum = 0.0;
            for (int j = 0; j < n; j++) {
                rowSum += Integer.parseInt(matrix[i][j]);
            }

            for (int j = 0; j < n; j++) {
                double actualValue = (i == j) ? 1.0 : 0.0;
                double predictedValue = Integer.parseInt(matrix[i][j]) / rowSum;
                double probability = predictedValue / (double) (predictedValue + (1 - predictedValue) * Math.exp(-actualValue));
                crossEntropy += -actualValue * Math.log(probability) - (1 - actualValue) * Math.log(1 - probability);
            }
        }

        result =  crossEntropy / (double) n;


    }

    @Override
    public DataSet retrieve(boolean clear) throws AnalysisException {
        return null;
    }

    @Override
    public double getResult() {
        return result;
    }
}
