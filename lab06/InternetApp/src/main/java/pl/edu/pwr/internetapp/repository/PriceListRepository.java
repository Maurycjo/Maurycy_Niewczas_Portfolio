package pl.edu.pwr.internetapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.pwr.internetapp.entity.PriceList;

public interface PriceListRepository extends JpaRepository<PriceList, Long> {
}
