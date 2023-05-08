package pl.edu.pwr.internetapp.service.implementation;
import org.springframework.stereotype.Service;
import pl.edu.pwr.internetapp.entity.*;
import pl.edu.pwr.internetapp.repository.ChargeRepository;
import pl.edu.pwr.internetapp.repository.InstallationRepository;
import pl.edu.pwr.internetapp.repository.PaymentRepository;
import pl.edu.pwr.internetapp.service.interfaces.iInstallationService;

import java.util.List;
import java.util.Optional;

@Service
public class InstallationService implements iInstallationService {
    private final InstallationRepository installationRepository;
    private final ClientService clientService;
    private final ServiceTypeService serviceTypeService;
    private final ChargeRepository chargeRepository;
    private final PaymentRepository paymentRepository;

    public InstallationService(InstallationRepository installationRepository, ClientService clientService, ServiceTypeService serviceTypeService, ChargeRepository chargeRepository, PaymentRepository paymentRepository) {
        this.installationRepository = installationRepository;
        this.clientService = clientService;
        this.serviceTypeService = serviceTypeService;
        this.chargeRepository = chargeRepository;
        this.paymentRepository = paymentRepository;
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

        List<Charge> chargeList = chargeRepository.findByInstallationId(id);


        for(var charge : chargeList){
            chargeRepository.deleteById(charge.getId());
        }

        List<Payment> paymentList = paymentRepository.findByInstallationId(id);

        for(var payment : paymentList){
            paymentRepository.deleteById(payment.getId());
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