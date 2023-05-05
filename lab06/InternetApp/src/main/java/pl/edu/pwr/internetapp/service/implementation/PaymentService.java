package pl.edu.pwr.internetapp.service.implementation;
import org.springframework.stereotype.Service;
import pl.edu.pwr.internetapp.entity.Payment;
import pl.edu.pwr.internetapp.service.interfaces.iPaymentService;

import java.time.LocalDate;
import java.util.List;

@Service
public class PaymentService implements iPaymentService{
    @Override
    public List<Payment> getInstallationPayments(Long installationId) {
        return null;
    }

    @Override
    public List<Payment> addInstallationPayment(LocalDate date, Float price, Long installationId) {
        return null;
    }

    @Override
    public List<Payment> updatePayment(Long paymentId, LocalDate date, Float price, Long installationId) {
        return null;
    }

    @Override
    public List<Payment> deletePayment(Long paymentId) {
        return null;
    }
}