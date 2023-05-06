package pl.edu.pwr.internetapp.service.implementation;

import org.springframework.stereotype.Service;
import pl.edu.pwr.internetapp.entity.ServiceType;
import pl.edu.pwr.internetapp.repository.ServiceTypeRepository;
import pl.edu.pwr.internetapp.service.interfaces.iServiceTypeService;

import java.util.List;

@Service
public class ServiceTypeService implements iServiceTypeService {

    private final ServiceTypeRepository serviceTypeRepository;

    public ServiceTypeService(ServiceTypeRepository serviceTypeRepository) {
        this.serviceTypeRepository = serviceTypeRepository;
    }

    @Override
    public ServiceType addServiceType(String serviceType, Double price) {
        return null;
    }

    @Override
    public ServiceType getServiceTypeById(Long id) {
        return null;
    }

    @Override
    public List<ServiceType> getAllServiceTypes() {
        return null;
    }

    @Override
    public void deleteServiceTypeById(Long id) {

    }
}