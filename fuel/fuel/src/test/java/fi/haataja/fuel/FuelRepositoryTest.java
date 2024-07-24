package fi.haataja.fuel;

import fi.haataja.fuel.model.ListResponse;
import fi.haataja.fuel.repository.FuelPurchase;
import fi.haataja.fuel.repository.FuelRepository;
import fi.haataja.fuel.rest.FuelController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.http.ResponseEntity;

import java.util.List;

@DataJpaTest
public class FuelRepositoryTest {

    @Autowired
    FuelRepository fuelRepository;

    @Autowired
    TestEntityManager entityManager;
    @Test
    void getAllPurchases() {
        List<FuelPurchase> list = fuelRepository.findAllByOrderByDateDesc();
    }
}
