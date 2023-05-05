package pl.edu.pwr.internetapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.pwr.internetapp.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
