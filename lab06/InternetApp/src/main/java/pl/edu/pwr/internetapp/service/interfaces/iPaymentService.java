package pl.edu.pwr.internetapp.service.interfaces;

import pl.edu.pwr.internetapp.entity.Payment;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface iPaymentService {

    Payment addPayment(LocalDate paymentDate, float amount, Long installationId);

    Payment getPaymentById(Long id);
    void deletePayment(Long id);

    List<Payment> getPaymentByInstallationId(Long installationId);

    List<Payment> getAllPayments();
}