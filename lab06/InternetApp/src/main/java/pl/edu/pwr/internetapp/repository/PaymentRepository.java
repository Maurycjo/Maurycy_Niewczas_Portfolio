package pl.edu.pwr.internetapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pwr.internetapp.entity.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
