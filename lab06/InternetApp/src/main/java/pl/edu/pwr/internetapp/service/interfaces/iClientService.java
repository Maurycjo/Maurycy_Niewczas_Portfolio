package pl.edu.pwr.internetapp.service.interfaces;

import pl.edu.pwr.internetapp.entity.Client;
import pl.edu.pwr.internetapp.entity.Installation;

import java.util.List;
import java.util.Optional;

public interface iClientService {
    Client addClient(String firstName, String lastName);
    Client modifyClient(Long clientId, String firstName, String lastName);
    List<Client> getAllClients();
    Client getClientById(Long id);
    void deleteClient(Long id);

}