package pl.edu.pwr.internetapp.service.implementation;
import org.springframework.stereotype.Service;
import pl.edu.pwr.internetapp.entity.Client;
import pl.edu.pwr.internetapp.entity.Installation;
import pl.edu.pwr.internetapp.entity.ServiceType;
import pl.edu.pwr.internetapp.repository.ClientRepository;
import pl.edu.pwr.internetapp.repository.InstallationRepository;
import pl.edu.pwr.internetapp.repository.ServiceTypeRepository;
import pl.edu.pwr.internetapp.service.interfaces.iInstallationService;

import java.util.List;
import java.util.Optional;

@Service
public class InstallationService implements iInstallationService {
    private final InstallationRepository installationRepository;
    private final ClientRepository clientRepository;
    private final ServiceTypeRepository serviceTypeRepository;

    public InstallationService(InstallationRepository installationRepository, ClientRepository clientRepository, ServiceTypeRepository serviceTypeRepository) {
        this.installationRepository = installationRepository;
        this.clientRepository = clientRepository;
        this.serviceTypeRepository = serviceTypeRepository;
    }

    @Override
    public Installation addInstallation(String address, String routerNumber, String serviceTypeName, Long clientId) {
        Optional<Client> clientOptional = clientRepository.findById(clientId);
        Client client = clientOptional.orElseThrow(()-> new RuntimeException("client not found with id: " + clientId));


        Optional<ServiceType> serviceTypeOptional = serviceTypeRepository.findServiceTypeByServiceName(serviceTypeName);
        ServiceType serviceType = serviceTypeOptional.orElseThrow(()-> new RuntimeException("service not found with name: " + serviceTypeName));

        Installation installation = new Installation(address, routerNumber, serviceType, client);
        return installationRepository.save(installation);

    }

    @Override
    public List<Installation> getAllInstallationsByClientId(Long clientId) {
        Optional<Client> client = clientRepository.findById(clientId);
        if(client==null) return null;
        return installationRepository.findByClientId(clientId);
    }

    @Override
    public Optional<Installation> getInstallationById(Long id) {
        return installationRepository.findById(id);
    }

    @Override
    public void deleteInstallation(Long id) {
        installationRepository.deleteById(id);
    }

    @Override
    public List<Installation> getAllInstallation() {
        return installationRepository.findAll();
    }

    @Override
    public List<Installation> getInstallationByServiceId(Long serviceId) {
        return serviceTypeRepository.findAllById(serviceId);
    }
}