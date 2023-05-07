package pl.edu.pwr.internetapp.service.interfaces;

import pl.edu.pwr.internetapp.entity.Installation;
import pl.edu.pwr.internetapp.service.implementation.InstallationService;

import java.util.List;

public interface iInstallationService {

    Installation addInstallation(String address, String routerNumber, String serviceType, Long clientId);
    List<Installation> getAllInstallationsByClientId(Long clientId);
    List<Installation> getAllInstallation();
    Installation getInstallationById(Long id);
    void deleteInstallation(Long id);

    List<Installation> getInstallationByServiceId(Long serviceId);
}