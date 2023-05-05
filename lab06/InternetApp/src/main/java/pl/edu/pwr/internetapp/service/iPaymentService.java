package pl.edu.pwr.internetapp.service;

import pl.edu.pwr.internetapp.entity.Payment;

import java.time.LocalDate;
import java.util.List;

public interface iPaymentService {

    List<Payment> getInstallationPayments(Long installationId);
    List<Payment> addInstallationPayment(LocalDate date, Float price, Long installationId);
    List<Payment> updatePayment(Long paymentId, LocalDate date, Float price, Long installationId);
    List<Payment> deletePayment(Long paymentId);

}