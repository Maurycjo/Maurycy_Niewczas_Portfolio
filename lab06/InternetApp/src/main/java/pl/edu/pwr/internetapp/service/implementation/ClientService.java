package pl.edu.pwr.internetapp.service.implementation;

import org.springframework.stereotype.Service;
import pl.edu.pwr.internetapp.entity.Client;
import pl.edu.pwr.internetapp.service.interfaces.iClientService;

import java.util.List;

@Service
public class ClientService implements iClientService{
    @Override
    public List<Client> getAll() {
        return null;
    }

    @Override
    public Client getById(Long id) {
        return null;
    }

    @Override
    public List<Client> addClient(String name, String surname) {
        return null;
    }

    @Override
    public Client updateClient(Long number, String name, String surname) {
        return null;
    }

    @Override
    public List<Client> deleteClient(Long clientId) {
        return null;
    }
}