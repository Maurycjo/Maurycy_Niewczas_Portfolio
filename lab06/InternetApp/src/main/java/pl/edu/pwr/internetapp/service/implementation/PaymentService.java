package pl.edu.pwr.internetapp.service.implementation;
import org.springframework.stereotype.Service;
import pl.edu.pwr.internetapp.entity.Payment;
import pl.edu.pwr.internetapp.repository.PaymentRepository;
import pl.edu.pwr.internetapp.service.interfaces.iPaymentService;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
public class PaymentService implements iPaymentService{

    @Override
    public Payment addPayment(Date paymentDate, float amount, Long chargeId) {
        return null;
    }

    @Override
    public List<Payment> getAllPaymentsByChargeId(Long chargeId) {
        return null;
    }

    @Override
    public Payment getPaymentById(Long id) {
        return null;
    }

    @Override
    public void deletePayment(Long id) {

    }
}