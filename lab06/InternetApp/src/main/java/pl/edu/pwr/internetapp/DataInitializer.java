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
import pl.edu.pwr.internetapp.service.implementation.*;
import pl.edu.pwr.internetapp.window.MainWindow;

import java.awt.*;
import java.time.LocalDate;
import java.util.Optional;

@Component
public class DataInitializer implements CommandLineRunner {


    private final MainWindow mainWindow;
    private final ClientService clientService;

    private final InstallationService installationService;

    private final ServiceTypeService serviceTypeService;

    private final PaymentService paymentService;

    private final ChargeService chargeService;



    public DataInitializer(ClientService clientService, InstallationService installationService, ServiceTypeService serviceTypeService, PaymentService paymentService, ChargeService chargeService, MainWindow mainWindow) {
        this.clientService = clientService;
        this.installationService = installationService;
        this.serviceTypeService = serviceTypeService;
        this.paymentService = paymentService;
        this.chargeService = chargeService;
        this.mainWindow = mainWindow;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {

        clientService.addClient("Maurycy", "Niewczas");
        clientService.addClient("Bartosz", "Niewczas");
        clientService.addClient("Ziemowit", "Wosna");
        clientService.addClient("Jaroslaw", "Jaroslaw");
        clientService.addClient("Filip", "Suseł");

        serviceTypeService.addServiceType("Full", 1000);
        serviceTypeService.addServiceType("Demo", 500);
        serviceTypeService.addServiceType("Premium", 3000);

        installationService.addInstallation("perlowa", "123C", serviceTypeService.getServiceTypeById(1L).getServiceName(), 1L);
        installationService.addInstallation("zimowa", "321A", serviceTypeService.getServiceTypeById(1L).getServiceName(), 1L);
        installationService.addInstallation("perlowa", "E432", serviceTypeService.getServiceTypeById(2L).getServiceName(), 1L);
        installationService.addInstallation("Szafirowa", "CJHA", serviceTypeService.getServiceTypeById(3L).getServiceName(), 2L);

        paymentService.addPayment(LocalDate.parse("2021-01-07"), 123, 1L);
        paymentService.addPayment(LocalDate.parse("2021-02-07"), 123, 1L);
        paymentService.addPayment(LocalDate.parse("2021-03-07"), 123, 2L);

        chargeService.addCharge(LocalDate.parse("2021-01-07"), 123, 1L);
        chargeService.addCharge(LocalDate.parse("2021-02-07"), 123, 1L);
        chargeService.addCharge(LocalDate.parse("2021-02-07"), 500, 4L);

        clientService.setInstallationServiceRef(installationService);


        EventQueue.invokeLater(()->mainWindow.setVisible(true));

    }
}
