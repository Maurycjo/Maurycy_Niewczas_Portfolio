package pl.edu.pwr.internetapp.service.implementation;

import org.springframework.stereotype.Service;
import pl.edu.pwr.internetapp.entity.Charge;
import pl.edu.pwr.internetapp.entity.Installation;
import pl.edu.pwr.internetapp.service.interfaces.iChargeService;

import java.time.LocalDate;
import java.util.List;

@Service
public class ChargeService implements iChargeService{
    @Override
    public List<Charge> getInstallationCharges(Long installationId) {
        return null;
    }

    @Override
    public Charge addCharge(LocalDate paymentDeadline, Float amountToPay, Boolean isPaid, Installation installationId) {
        return null;
    }

    @Override
    public void updateChargePaid(Long id, LocalDate paymentDeadline, Float amountToPay, Boolean isPaid, Installation installationId) {

    }
}
