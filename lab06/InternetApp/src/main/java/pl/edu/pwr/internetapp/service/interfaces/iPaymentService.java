package pl.edu.pwr.internetapp.service.interfaces;

import pl.edu.pwr.internetapp.entity.Payment;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface iPaymentService {

    Payment addPayment(Date paymentDate, float amount, Long chargeId);
    List<Payment> getAllPaymentsByChargeId(Long chargeId);
    Payment getPaymentById(Long id);
    void deletePayment(Long id);
}