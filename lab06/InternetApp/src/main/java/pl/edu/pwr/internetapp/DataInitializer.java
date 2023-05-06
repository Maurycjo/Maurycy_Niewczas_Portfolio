package pl.edu.pwr.internetapp;

import jakarta.persistence.Column;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.edu.pwr.internetapp.entity.Client;
import pl.edu.pwr.internetapp.repository.ClientRepository;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    @Transactional
    public void run(String... args) throws Exception {





    }
}
