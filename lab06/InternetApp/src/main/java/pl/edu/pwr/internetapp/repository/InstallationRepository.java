package pl.edu.pwr.internetapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.pwr.internetapp.entity.Installation;

public interface InstallationRepository extends JpaRepository<Installation, Long> {
}
