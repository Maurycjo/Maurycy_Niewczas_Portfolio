package pl.edu.pwr.internetapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.pwr.internetapp.entity.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
