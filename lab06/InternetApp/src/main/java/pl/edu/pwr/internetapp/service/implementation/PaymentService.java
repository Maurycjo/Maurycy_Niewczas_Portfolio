package pl.edu.pwr.internetapp.service.implementation;
import org.springframework.stereotype.Service;
import pl.edu.pwr.internetapp.entity.Installation;
import pl.edu.pwr.internetapp.entity.Payment;
import pl.edu.pwr.internetapp.repository.InstallationRepository;
import pl.edu.pwr.internetapp.repository.PaymentRepository;
import pl.edu.pwr.internetapp.service.interfaces.iPaymentService;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentService implements iPaymentService{

    private final InstallationRepository installationRepository;
    private final PaymentRepository paymentRepository;

    public PaymentService(InstallationRepository installationRepository, PaymentRepository paymentRepository) {
        this.installationRepository = installationRepository;
        this.paymentRepository = paymentRepository;
    }

    @Override
    public Payment addPayment(LocalDate paymentDate, float amount, Long installationId) {
        Optional<Installation> installationOptional = installationRepository.findById(installationId);
        Installation installation = installationOptional.orElseThrow(()-> new RuntimeException("Installation not found with id: " + installationId));

        Payment payment = new Payment(paymentDate, amount, installation);
        return paymentRepository.save(payment);
    }


    @Override
    public Payment getPaymentById(Long id) {
        Optional<Payment> paymentOptional = paymentRepository.findById(id);
        Payment payment = paymentOptional.orElseThrow(()-> new RuntimeException("Payment not found with id: " + id));
        return payment;
    }

    @Override
    public void deletePayment(Long id) {

    }
}