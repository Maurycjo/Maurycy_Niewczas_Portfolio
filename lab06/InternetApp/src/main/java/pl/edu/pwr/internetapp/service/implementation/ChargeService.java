package pl.edu.pwr.internetapp.service.implementation;

import org.springframework.stereotype.Service;
import pl.edu.pwr.internetapp.entity.Charge;
import pl.edu.pwr.internetapp.entity.Installation;
import pl.edu.pwr.internetapp.repository.ChargeRepository;
import pl.edu.pwr.internetapp.repository.InstallationRepository;
import pl.edu.pwr.internetapp.service.interfaces.iChargeService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ChargeService implements iChargeService{

    private final ChargeRepository chargeRepository;
    private final InstallationRepository installationRepository;

    public ChargeService(ChargeRepository chargeRepository, InstallationRepository installationRepository) {
        this.chargeRepository = chargeRepository;
        this.installationRepository = installationRepository;
    }

    @Override
    public Charge addCharge(LocalDate paymentDeadline, float amountToPay, Long installationId) {

        Optional<Installation> installationOptional = installationRepository.findById(installationId);
        Installation installation = installationOptional.orElseThrow(()->new RuntimeException("Installation not found with id: " + installationId));
        Charge charge = new Charge(paymentDeadline, amountToPay, installation);
        return chargeRepository.save(charge);
    }

    @Override
    public List<Charge> getAllChargesByInstallationId(Long installationId) {
        //return chargeRepository.findAllByInstallation_Id(installationId);
        //return chargeRepository.findByInstallationId(installationId);
        return null;
    }

    @Override
    public List<Charge> getAllCharges() {
        return chargeRepository.findAll();
    }

    @Override
    public Charge getChargeById(Long id) {
        Optional<Charge> chargeOptional = chargeRepository.findById(id);
        return chargeOptional.orElseThrow(()-> new RuntimeException("Charge not found with id: " + id));
    }

    @Override
    public void deleteCharge(Long id) {
        chargeRepository.deleteById(id);
    }

    @Override
    public Charge updateCharge(Long id, LocalDate paymentDeadline, float amountToPay, Long installationId) {

        Optional<Charge> chargeOptional = chargeRepository.findById(id);
        Charge charge = chargeOptional.orElseThrow(()-> new RuntimeException("Charge not found with id: " + id));
        charge.setPaymentDeadline(paymentDeadline);
        charge.setAmountToPay(amountToPay);

        Optional<Installation> installationOptional = installationRepository.findById(installationId);
        Installation installation = installationOptional.orElseThrow(()->new RuntimeException("Installation not found wit id: "+installationId));
        charge.setInstallation(installation);

        return charge;

    }
}
