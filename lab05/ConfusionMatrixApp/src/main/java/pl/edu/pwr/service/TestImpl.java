package pl.edu.pwr.service;

import pl.edu.pwr.ex.api.ClusterAnalysisService;
import pl.edu.pwr.ex.api.ClusteringException;
import pl.edu.pwr.ex.api.DataSet;

public class TestImpl implements ClusterAnalysisService {

    @Override
    public void setOptions(String[] options) throws ClusteringException {

    }

    @Override
    public String getName() {
        return "Test";
    }

    @Override
    public void submit(DataSet ds) throws ClusteringException {

    }

    @Override
    public DataSet retrieve(boolean clear) throws ClusteringException {
        return null;
    }
}
