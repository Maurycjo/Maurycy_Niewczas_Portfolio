package pl.edu.pwr.internetapp.service.interfaces;

import pl.edu.pwr.internetapp.entity.Charge;

import java.time.LocalDate;
import java.util.List;

public interface iChargeService {

    Charge addCharge(LocalDate paymentDeadline, float amountToPay, Long installationId);
    List<Charge> getAllChargesByInstallationId(Long installationId);
    List<Charge> getAllCharges();
    Charge getChargeById(Long id);
    void deleteCharge(Long id);
    Charge updateCharge(Long id, LocalDate paymentDeadline, float amountToPay, Long installationId);
}
