package pl.edu.pwr.internetapp.service.interfaces;

import pl.edu.pwr.internetapp.entity.ServiceType;

import java.util.List;

public interface iServiceTypeService {

    ServiceType addServiceType(String serviceType, Double price);
    ServiceType getServiceTypeById(Long id);
    List<ServiceType> getAllServiceTypes();
    void deleteServiceTypeById(Long id);

}