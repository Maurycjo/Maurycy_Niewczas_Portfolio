package pl.edu.pwr.internetapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pwr.internetapp.entity.Charge;
import pl.edu.pwr.internetapp.entity.Installation;

import java.util.List;

@Repository
public interface ChargeRepository extends JpaRepository<Charge, Long> {

    List<Charge> findByInstallationId(Long installationId);
    List<Charge> findAllByInstallation_Id(Long installationId);
}
