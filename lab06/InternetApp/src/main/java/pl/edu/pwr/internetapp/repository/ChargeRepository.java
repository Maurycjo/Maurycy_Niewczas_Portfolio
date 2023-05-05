package pl.edu.pwr.internetapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.pwr.internetapp.entity.Charge;

public interface ChargeRepository extends JpaRepository<Charge, Long> {
}
