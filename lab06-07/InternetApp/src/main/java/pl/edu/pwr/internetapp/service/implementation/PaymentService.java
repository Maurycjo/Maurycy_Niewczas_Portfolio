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

    private final PaymentRepository paymentRepository;
    private final InstallationService installationService;

    public PaymentService(InstallationService installationService, PaymentRepository paymentRepository) {
        this.installationService = installationService;
        this.paymentRepository = paymentRepository;
    }

    @Override
    public Payment addPayment(LocalDate paymentDate, float amount, Long installationId) {

        Installation installation = installationService.getInstallationById(installationId);
        Payment payment = new Payment(paymentDate, amount, installation);
        return paymentRepository.save(payment);
    }


    @Override
    public Payment getPaymentById(Long id) {
        Optional<Payment> paymentOptional = paymentRepository.findById(id);
        return paymentOptional.orElseThrow(()-> new RuntimeException("Payment not found with id: " + id));
    }

    @Override
    public List<Payment> getPaymentByInstallationId(Long installationId) {
        return paymentRepository.findByInstallationId(installationId);
    }

    @Override
    public void deletePayment(Long id) {
    paymentRepository.deleteById(id);
    }

    @Override
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }
}