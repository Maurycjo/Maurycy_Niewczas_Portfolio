package pl.edu.pwr.internetapp.service.interfaces;

import pl.edu.pwr.internetapp.entity.PriceList;

import java.util.List;

public interface iPriceListService {

    List<PriceList> getAllServices();
    PriceList getById(Long id);
    List<PriceList> addService(String type, Float price);
    PriceList updateService(Long id, String type, Float price);
    List<PriceList> deleteService(Long id);

}