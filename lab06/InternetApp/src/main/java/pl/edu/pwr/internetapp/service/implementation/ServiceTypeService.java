package pl.edu.pwr.internetapp.service.implementation;

import org.springframework.stereotype.Service;
import pl.edu.pwr.internetapp.entity.ServiceType;
import pl.edu.pwr.internetapp.repository.ServiceTypeRepository;
import pl.edu.pwr.internetapp.service.interfaces.iServiceTypeService;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceTypeService implements iServiceTypeService {

    private final ServiceTypeRepository serviceTypeRepository;

    public ServiceTypeService(ServiceTypeRepository serviceTypeRepository) {
        this.serviceTypeRepository = serviceTypeRepository;
    }

    @Override
    public ServiceType addServiceType(String serviceName, float price) {
        ServiceType serviceType = new ServiceType(serviceName, price);
        return serviceTypeRepository.save(serviceType);
    }

    @Override
    public ServiceType getServiceTypeById(Long id) {
        Optional<ServiceType> serviceTypeOptional = serviceTypeRepository.findById(id);
        return serviceTypeOptional.orElseThrow(()-> new RuntimeException("Service not found with id: " + id));
    }

    @Override
    public List<ServiceType> getAllServiceTypes() {
        return serviceTypeRepository.findAll();
    }

    @Override
    public void deleteServiceTypeById(Long id) {
        serviceTypeRepository.deleteById(id);
    }

    @Override
    public ServiceType getServiceTypeByServiceName(String serviceName) {
        Optional<ServiceType> serviceTypeOptional = serviceTypeRepository.findServiceTypeByServiceName(serviceName);
        return serviceTypeOptional.orElseThrow(()-> new RuntimeException("service not found with name: " + serviceName));
    }
}