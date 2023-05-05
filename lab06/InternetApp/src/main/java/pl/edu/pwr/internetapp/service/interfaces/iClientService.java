package pl.edu.pwr.internetapp.service.interfaces;

import pl.edu.pwr.internetapp.entity.Client;

import java.util.List;

public interface iClientService {

    List<Client> getAll();
    Client getById(Long id);
    List<Client> addClient(String name, String surname);
    Client updateClient(Long number,String name, String surname);
    List<Client> deleteClient(Long clientId);
}