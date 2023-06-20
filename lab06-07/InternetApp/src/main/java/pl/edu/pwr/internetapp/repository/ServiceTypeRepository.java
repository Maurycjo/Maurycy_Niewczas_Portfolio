package pl.edu.pwr.internetapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pwr.internetapp.entity.Installation;
import pl.edu.pwr.internetapp.entity.ServiceType;

import java.util.List;
import java.util.Optional;

@Repository
public interface ServiceTypeRepository extends JpaRepository<ServiceType, Long> {
    Optional<ServiceType> findServiceTypeByServiceName(String serviceTypeName);

    List<Installation> findAllById(Long serviceId);

    Optional<ServiceType> findByServiceName(String serviceTypeName);


}


