package pl.edu.pwr.internetapp.service.implementation;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import pl.edu.pwr.internetapp.entity.*;
import pl.edu.pwr.internetapp.repository.ClientRepository;
import pl.edu.pwr.internetapp.service.interfaces.iClientService;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ClientService implements iClientService{

    private final ClientRepository clientRepository;
    private InstallationService installationServiceRef;


    public void setInstallationServiceRef(InstallationService installationService){
        installationServiceRef = installationService;
    }
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Client addClient(String firstName, String lastName) {
        Client client = new Client(firstName, lastName);
        return clientRepository.save(client);
    }

    @Override
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    @Override
    public Client getClientById(Long id) {
        Optional<Client> clientOptional = clientRepository.findById(id);
        return clientOptional.orElseThrow(()-> new RuntimeException("Client not found with id: " + id));
    }

    @Override
    public void deleteClient(Long id) {

    Optional<Client> clientOptional = clientRepository.findById(id);
    if(clientOptional == null) return;

    Client client = clientOptional.orElseThrow(()-> new RuntimeException("Client not found wit id: " + id));

    List<Installation> installationList = installationServiceRef.getAllInstallationsByClientId(client.getId());
    if(installationList.isEmpty() || installationList==null){
        clientRepository.deleteById(id);
        return;
    }

    for(var installation : installationList){
        installationServiceRef.deleteInstallation(installation.getId());
    }
    clientRepository.deleteById(id);

    }

    @Override
    public Client modifyClient(Long clientId, String firstName, String lastName) {
        Optional<Client> clientOptional = clientRepository.findById(clientId);

        if(clientOptional == null) return null;
        Client client = clientOptional.orElseThrow(()-> new RuntimeException("Client not found with id: " + clientId));

        client.setFirstName(firstName);
        client.setLastName(lastName);
        return  clientRepository.save(client);
    }


}