package pl.edu.pwr.internetapp.service;

import pl.edu.pwr.internetapp.entity.Installation;

import java.util.List;
import java.util.Optional;

public interface iInstallation {

    List<Installation> getAll();
    List<Installation> getClientInstallations(Long clientId);
    Optional<Installation> getInstallation(Long id);
    List<Installation> addClientInstallations(String address, Long routerNumber, Long serviceId,
                                              Long clientId);
    Installation updateInstallation(Long id, String address, Long routerNumber,
                                    Long priceListId, Long clientId);
    List<Installation> deleteInstallation(Long id);
    List<Installation> getServiceInstallations(Long serviceId);
}