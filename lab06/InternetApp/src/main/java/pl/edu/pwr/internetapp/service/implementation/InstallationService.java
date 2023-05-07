package pl.edu.pwr.internetapp.service.implementation;
import org.springframework.stereotype.Service;
import pl.edu.pwr.internetapp.entity.*;
import pl.edu.pwr.internetapp.repository.InstallationRepository;
import pl.edu.pwr.internetapp.service.interfaces.iInstallationService;

import java.util.List;
import java.util.Optional;

@Service
public class InstallationService implements iInstallationService {
    private final InstallationRepository installationRepository;
    private final ClientService clientService;
    private final ServiceTypeService serviceTypeService;
    private final ChargeService chargeService;
    private final PaymentService paymentService;

    public InstallationService(InstallationRepository installationRepository, ClientService clientService, ServiceTypeService serviceTypeService, ChargeService chargeService, PaymentService paymentService) {
        this.installationRepository = installationRepository;
        this.clientService = clientService;
        this.serviceTypeService = serviceTypeService;
        this.chargeService = chargeService;
        this.paymentService = paymentService;
    }

    @Override
    public Installation addInstallation(String address, String routerNumber, String serviceTypeName, Long clientId) {

        Client client = clientService.getClientById(clientId);
        ServiceType serviceType = serviceTypeService.getServiceTypeByServiceName(serviceTypeName);
        Installation installation = new Installation(address, routerNumber, serviceType, client);
        return installationRepository.save(installation);

    }

    @Override
    public List<Installation> getAllInstallationsByClientId(Long clientId) {
        return installationRepository.findByClientId(clientId);
    }

    @Override
    public Installation getInstallationById(Long id) {
        Optional<Installation> installationOptional = installationRepository.findById(id);
        return installationOptional.orElseThrow(()->new RuntimeException("Installation not found with id: " + id));
    }

    @Override
    public void deleteInstallation(Long id) {

        List<Charge> chargeList = chargeService.getAllChargesByInstallationId(id);

        for(var charge : chargeList){
            chargeService.deleteCharge(charge.getId());
        }

        List<Payment> paymentList = paymentService.getPaymentByInstallationId(id);

        for(var payment : paymentList){
            paymentService.deletePayment(payment.getId());
        }

        installationRepository.deleteById(id);
    }

    @Override
    public List<Installation> getAllInstallation() {
        return installationRepository.findAll();
    }

    @Override
    public List<Installation> getInstallationByServiceId(Long serviceId) {
        return installationRepository.findByServiceTypeId(serviceId);
    }
}