package pl.edu.pwr.internetapp.service.interfaces;

import pl.edu.pwr.internetapp.entity.Charge;
import pl.edu.pwr.internetapp.entity.Installation;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface iChargeService {

    Charge addCharge(LocalDate paymentDeadline, float amountToPay, Long installationId);
    List<Charge> getAllChargesByInstallationId(Long installationId);
    Optional<Charge> getChargeById(Long id);
    void deleteCharge(Long id);

    void updateCharge(Long id, LocalDate paymentDeadline, float amountToPay, Installation installation);
}
