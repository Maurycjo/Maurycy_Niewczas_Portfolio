package pl.edu.pwr.internetapp.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pwr.internetapp.entity.Charge;
import pl.edu.pwr.internetapp.entity.Installation;
import pl.edu.pwr.internetapp.repository.ChargeRepository;
import pl.edu.pwr.internetapp.service.interfaces.iChargeService;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ChargeService implements iChargeService{

    @Override
    public Charge addCharge(Date paymentDeadline, float amountToPay, Long installationId) {
        return null;
    }

    @Override
    public List<Charge> getAllChargesByInstallationId(Long installationId) {
        return null;
    }

    @Override
    public Charge getChargeById(Long id) {
        return null;
    }

    @Override
    public void deleteCharge(Long id) {

    }
}
