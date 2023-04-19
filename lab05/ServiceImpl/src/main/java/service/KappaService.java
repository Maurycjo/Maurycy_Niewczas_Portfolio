package service;

import ex.api.AnalysisException;
import ex.api.AnalysisService;
import ex.api.DataSet;

public class KappaService implements AnalysisService {


    private double result;
    @Override
    public void setOptions(String[] options) throws AnalysisException {

    }

    @Override
    public String getName() {
        return "Kappa";
    }

    @Override
    public void submit(DataSet ds) throws AnalysisException {
        double po = 0.0; // observed agreement
        double pe = 0.0; // expected agreement
        String[][] matrix = ds.getData();
        int n = matrix.length;

        // calculate total observations (N)
        double N = 0.0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                N += Integer.parseInt(matrix[i][j]);
            }
        }

        // calculate observed agreement (po)
        for (int i = 0; i < n; i++) {
            po += Integer.parseInt(matrix[i][i]);
        }
        po /= N;

        // calculate expected agreement (pe)
        double[] rowSums = new double[n];
        double[] colSums = new double[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                rowSums[i] += Integer.parseInt(matrix[i][j]);
                colSums[j] += Integer.parseInt(matrix[i][j]);
            }
        }
        for (int i = 0; i < n; i++) {
            pe += rowSums[i] * colSums[i];
        }
        pe /= (N * N);

        // calculate kappa
        result = (po - pe) / (1 - pe);


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
