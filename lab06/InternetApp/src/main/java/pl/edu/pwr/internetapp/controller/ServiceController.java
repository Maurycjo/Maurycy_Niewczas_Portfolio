package pl.edu.pwr.internetapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.edu.pwr.internetapp.entity.ServiceType;
import pl.edu.pwr.internetapp.service.implementation.ServiceTypeService;

import java.util.List;

@RestController
public class ServiceController {

    @Autowired
    private final ServiceTypeService serviceTypeService;

    public ServiceController(ServiceTypeService serviceTypeService) {
        this.serviceTypeService = serviceTypeService;
    }

    @GetMapping("/services")
    List<ServiceType> getAllServices(){
        return serviceTypeService.getAllServiceTypes();
    }

    @GetMapping("services/{id}")
    ServiceType getOneService(@PathVariable Long id){
        return serviceTypeService.getServiceTypeById(id);
    }

    @PostMapping("/services/")
    ServiceType newServiceType(@RequestParam String serviceName, float price){
        return serviceTypeService.addServiceType(serviceName, price);
    }


}
