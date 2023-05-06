package pl.edu.pwr.internetapp.service.interfaces;

import pl.edu.pwr.internetapp.entity.Charge;
import pl.edu.pwr.internetapp.entity.Installation;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface iChargeService {

    Charge addCharge(Date paymentDeadline, float amountToPay, Long installationId);
    List<Charge> getAllChargesByInstallationId(Long installationId);
    Charge getChargeById(Long id);
    void deleteCharge(Long id);
}
