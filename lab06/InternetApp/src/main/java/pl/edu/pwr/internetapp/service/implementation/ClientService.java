package pl.edu.pwr.internetapp.service.implementation;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import pl.edu.pwr.internetapp.entity.*;
import pl.edu.pwr.internetapp.repository.ClientRepository;
import pl.edu.pwr.internetapp.repository.InstallationRepository;
import pl.edu.pwr.internetapp.service.interfaces.iClientService;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ClientService implements iClientService{

    private final ClientRepository clientRepository;
    private final InstallationRepository installationRepository;

    public ClientService(ClientRepository clientRepository, InstallationRepository installationRepository) {
        this.clientRepository = clientRepository;
        this.installationRepository = installationRepository;
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
    public Optional<Client> getClientById(Long id) {
        return clientRepository.findById(id);
    }

    @Override
    public void deleteClient(Long id) {
        clientRepository.deleteById(id);

    }

    @Override
    public Client modifyClient(String firstName, String lastName) {
        Client client = clientRepository.findByFirstNameAndLastName(firstName, lastName);
        if(client == null){
            return null;
        }
        client.setFirstName(firstName);
        client.setLastName(lastName);
        return  clientRepository.save(client);
    }
}