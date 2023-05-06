package pl.edu.pwr.internetapp.service.interfaces;

import pl.edu.pwr.internetapp.entity.Installation;

import java.util.List;
import java.util.Optional;

public interface iInstallation {

    Installation addInstallation(String address, String routerNumber, String serviceType, Long clientId);
    List<Installation> getAllInstallationsByClientId(Long clientId);
    Installation getInstallationById(Long id);
    void deleteInstallation(Long id);
}