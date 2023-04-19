package service;

import ex.api.AnalysisException;
import ex.api.AnalysisService;
import ex.api.DataSet;
public class BalancedAccuracy implements AnalysisService {

    private double result;
    @Override
    public void setOptions(String[] options) throws AnalysisException {

    }

    @Override
    public String getName() {
        return "Balanced Accuracy";
    }

    @Override
    public void submit(DataSet ds) throws AnalysisException {

        String[][] matrix = ds.getData();

        int n = matrix.length;
        double[] truePositives = new double[n];
        double[] falsePositives = new double[n];
        double[] trueNegatives = new double[n];
        double[] falseNegatives = new double[n];

        // Calculate true positives, false positives, true negatives, false negatives for each class
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    truePositives[i] += Integer.valueOf(matrix[i][j]);
                } else {
                    falsePositives[i] += Integer.valueOf(matrix[j][i]);
                    falseNegatives[i] += Integer.valueOf(matrix[i][j]);
                }
                trueNegatives[i] += Integer.valueOf(matrix[j][j]);
            }
        }

        // Calculate balanced accuracy
        double balancedAccuracy = 0.0;
        for (int i = 0; i < n; i++) {
            double sensitivity = 0.0;
            double specificity = 0.0;
            if (truePositives[i] + falseNegatives[i] != 0) {
                sensitivity = truePositives[i] / (truePositives[i] + falseNegatives[i]);
            }
            if (trueNegatives[i] + falsePositives[i] != 0) {
                specificity = trueNegatives[i] / (trueNegatives[i] + falsePositives[i]);
            }
            balancedAccuracy += (sensitivity + specificity) / 2;
        }
        balancedAccuracy /= n;

        result = balancedAccuracy;
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
