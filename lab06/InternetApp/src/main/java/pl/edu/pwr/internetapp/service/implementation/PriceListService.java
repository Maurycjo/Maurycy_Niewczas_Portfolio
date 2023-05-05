package pl.edu.pwr.internetapp.service.implementation;

import org.springframework.stereotype.Service;
import pl.edu.pwr.internetapp.entity.PriceList;
import pl.edu.pwr.internetapp.service.interfaces.iPriceListService;

import java.util.List;

@Service
public class PriceListService implements iPriceListService{
    @Override
    public List<PriceList> getAllServices() {
        return null;
    }

    @Override
    public PriceList getById(Long id) {
        return null;
    }

    @Override
    public List<PriceList> addService(String type, Float price) {
        return null;
    }

    @Override
    public PriceList updateService(Long id, String type, Float price) {
        return null;
    }

    @Override
    public List<PriceList> deleteService(Long id) {
        return null;
    }
}