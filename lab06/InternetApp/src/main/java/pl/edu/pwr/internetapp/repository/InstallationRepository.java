package pl.edu.pwr.internetapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pwr.internetapp.entity.Installation;
import pl.edu.pwr.internetapp.entity.ServiceType;

import java.util.List;

@Repository
public interface InstallationRepository extends JpaRepository<Installation, Long> {

    List<Installation> findByClientId(Long clientId);
    List<Installation> findByServiceTypeId(Long serviceId);


}
