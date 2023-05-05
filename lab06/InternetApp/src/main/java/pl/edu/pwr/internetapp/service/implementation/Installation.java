package pl.edu.pwr.internetapp.service.implementation;
import org.springframework.stereotype.Service;
import pl.edu.pwr.internetapp.service.interfaces.iInstallation;

import java.util.List;
import java.util.Optional;

@Service
public class Installation implements iInstallation{
    @Override
    public List<pl.edu.pwr.internetapp.entity.Installation> getAll() {
        return null;
    }

    @Override
    public List<pl.edu.pwr.internetapp.entity.Installation> getClientInstallations(Long clientId) {
        return null;
    }

    @Override
    public Optional<pl.edu.pwr.internetapp.entity.Installation> getInstallation(Long id) {
        return Optional.empty();
    }

    @Override
    public List<pl.edu.pwr.internetapp.entity.Installation> addClientInstallations(String address, Long routerNumber, Long serviceId, Long clientId) {
        return null;
    }

    @Override
    public pl.edu.pwr.internetapp.entity.Installation updateInstallation(Long id, String address, Long routerNumber, Long priceListId, Long clientId) {
        return null;
    }

    @Override
    public List<pl.edu.pwr.internetapp.entity.Installation> deleteInstallation(Long id) {
        return null;
    }

    @Override
    public List<pl.edu.pwr.internetapp.entity.Installation> getServiceInstallations(Long serviceId) {
        return null;
    }
}