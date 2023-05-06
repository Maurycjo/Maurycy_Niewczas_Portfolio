package pl.edu.pwr.internetapp.service.implementation;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import pl.edu.pwr.internetapp.entity.*;
import pl.edu.pwr.internetapp.repository.ClientRepository;
import pl.edu.pwr.internetapp.repository.InstallationRepository;
import pl.edu.pwr.internetapp.service.interfaces.iClientService;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ClientService implements iClientService{
    @Override
    public Client addClient(String firstName, String lastName) {
        return null;
    }

    @Override
    public List<Client> getAllClients() {
        return null;
    }

    @Override
    public Client getClientById(Long id) {
        return null;
    }

    @Override
    public void deleteClient(Long id) {

    }
}