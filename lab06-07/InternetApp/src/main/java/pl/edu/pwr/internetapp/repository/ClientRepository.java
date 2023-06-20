package pl.edu.pwr.internetapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pwr.internetapp.entity.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    Client findByFirstNameAndLastName(String firstName, String lastName);
}
