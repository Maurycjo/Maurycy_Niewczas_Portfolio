package pl.edu.pwr.internetapp.service.implementation;
import org.springframework.stereotype.Service;
import pl.edu.pwr.internetapp.entity.Installation;
import pl.edu.pwr.internetapp.service.interfaces.iInstallation;

import java.util.List;

@Service
public class InstallationService implements iInstallation{
    @Override
    public Installation addInstallation(String address, String routerNumber, String serviceType, Long clientId) {
        return null;
    }

    @Override
    public List<Installation> getAllInstallationsByClientId(Long clientId) {
        return null;
    }

    @Override
    public Installation getInstallationById(Long id) {
        return null;
    }

    @Override
    public void deleteInstallation(Long id) {

    }
}