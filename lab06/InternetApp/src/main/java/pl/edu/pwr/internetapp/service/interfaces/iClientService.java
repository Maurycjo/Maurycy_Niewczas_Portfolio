package pl.edu.pwr.internetapp.service.interfaces;

import pl.edu.pwr.internetapp.entity.Client;

import java.util.List;

public interface iClientService {
    Client addClient(String firstName, String lastName);
    List<Client> getAllClients();
    Client getClientById(Long id);
    void deleteClient(Long id);

}