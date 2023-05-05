package pl.edu.pwr.internetapp.service.interfaces;

import pl.edu.pwr.internetapp.entity.Charge;
import pl.edu.pwr.internetapp.entity.Installation;

import java.time.LocalDate;
import java.util.List;

public interface iChargeService {
    List<Charge> getInstallationCharges(Long installationId);
    Charge addCharge(LocalDate paymentDeadline, Float amountToPay, Boolean isPaid, Installation installationId);
    void updateChargePaid(Long id, LocalDate paymentDeadline, Float amountToPay, Boolean isPaid, Installation installationId);
}
