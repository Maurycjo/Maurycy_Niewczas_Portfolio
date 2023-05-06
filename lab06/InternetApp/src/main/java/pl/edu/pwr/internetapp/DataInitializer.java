package pl.edu.pwr.internetapp;

import jakarta.persistence.Column;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.edu.pwr.internetapp.entity.Client;
import pl.edu.pwr.internetapp.entity.Installation;
import pl.edu.pwr.internetapp.entity.ServiceType;
import pl.edu.pwr.internetapp.repository.*;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private  ClientRepository clientRepository;

    @Autowired
    private InstallationRepository installationRepository;

    @Autowired
    private ServiceTypeRepository serviceTypeRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    private ChargeRepository chargeRepository;


    @Override
    @Transactional
    public void run(String... args) throws Exception {

        Client client1 = new Client("Maurycy", "Niewczas");
        Client client2 = new Client("Bartosz", "Niewczas");
        Client client3 = new Client("Mirek", "Niewczas");

        ServiceType serviceType1 = new ServiceType("Full", 1000);
        ServiceType serviceType2 = new ServiceType("Demo", 10);
        ServiceType serviceType3 = new ServiceType("Part", 500);
        ServiceType serviceType4 = new ServiceType("Premium", 2000);

        Installation installation1 = new Installation("Perlowa 12", "123A", serviceType4, client1);
        Installation installation2 = new Installation("Perlowa 11", "124A", serviceType1, client1);
        Installation installation3 = new Installation("Kwiatowa", "33AC", serviceType2, client2);


        clientRepository.save(client1);
        clientRepository.save(client2);
        clientRepository.save(client3);

        serviceTypeRepository.save(serviceType1);
        serviceTypeRepository.save(serviceType2);
        serviceTypeRepository.save(serviceType3);
        serviceTypeRepository.save(serviceType4);

        installationRepository.save(installation1);
        installationRepository.save(installation2);
        installationRepository.save(installation3);


    }
}
