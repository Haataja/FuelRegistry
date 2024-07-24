package fi.haataja.fuel;

import fi.haataja.fuel.model.ListResponse;
import fi.haataja.fuel.repository.FuelPurchase;
import fi.haataja.fuel.service.DataService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FuelControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    DataService dataService;

    @Test
    void getAllPurchasesWithEmptyDatabase() {
        when(dataService.findAll()).thenReturn(new ArrayList<>());

        ResponseEntity<ListResponse> response = restTemplate.getForEntity("http://localhost:" + port + "/v2/purchases", ListResponse.class);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(0, Objects.requireNonNull(response.getBody()).getSize());
    }

    @Test
    void getAllPurchases() {
        when(dataService.findAll()).thenReturn(getTestData());

        ResponseEntity<ListResponse> response = restTemplate.getForEntity("http://localhost:" + port + "/v2/purchases", ListResponse.class);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(3, Objects.requireNonNull(response.getBody()).getSize());
        FuelPurchase first = response.getBody().getData().get(0);
        Assertions.assertEquals(105, first.getId());
        Assertions.assertEquals(106, response.getBody().getData().get(1).getId());
        Assertions.assertEquals(107, response.getBody().getData().get(2).getId());
    }

    @Test
    void getAllPurchasesAfterDate() {
        when(dataService.findAllByDate(LocalDate.parse("2011-05-01"))).thenReturn(getTestData());

        ResponseEntity<ListResponse> response = restTemplate.getForEntity("http://localhost:" + port + "/v2/purchases?date=2011-05-01", ListResponse.class);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(3, Objects.requireNonNull(response.getBody()).getSize());
        FuelPurchase first = response.getBody().getData().get(0);
        Assertions.assertEquals(105, first.getId());
        Assertions.assertEquals(106, response.getBody().getData().get(1).getId());
        Assertions.assertEquals(107, response.getBody().getData().get(2).getId());
    }

    /*
    @Test
    void postPurchase() {
        FuelPurchase first = getTestData().get(0);
        when(dataService.save(first)).thenReturn(first);

        ResponseEntity<FuelPurchase> response = restTemplate.postForEntity("http://localhost:" + port + "/purchases", first, FuelPurchase.class);
        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
        System.out.println(response.toString());
        Assertions.assertEquals(105, Objects.requireNonNull(response.getBody()).getId());
    }
    */

    @Test
    void getPurchaseWithId() {
        FuelPurchase first = getTestData().get(0);
        when(dataService.findById(first.getId())).thenReturn(new ResponseEntity<>(first, HttpStatus.OK));

        ResponseEntity<FuelPurchase> response = restTemplate.getForEntity("http://localhost:" + port + "/purchases/105", FuelPurchase.class);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(105, Objects.requireNonNull(response.getBody()).getId());
    }


    private List<FuelPurchase> getTestData(){
        /*
    {
        "id": 107,
        "date": "2018-11-27",
        "mileage": 85374,
        "litre": 54.04999924,
        "pricePerLitre": 1.458999991,
        "price": 78.85894775,
        "location": "Neste Express Nokia",
        "description": "Dieseliä",
        "credit": false
    },
    {
        "id": 106,
        "date": "2018-11-08",
        "mileage": 84537,
        "litre": 59.0,
        "pricePerLitre": 1.493999958,
        "price": 88.14599609,
        "location": "Neste Express Nokia",
        "description": "Dieseliä",
        "credit": false
    },
    {
        "id": 105,
        "date": "2018-10-28",
        "mileage": 83623,
        "litre": 43.00999832,
        "pricePerLitre": 1.43900001,
        "price": 61.89138794,
        "location": "Neste Express Nokia",
        "description": "Dieseliä",
        "credit": false
    },
         */
        List<FuelPurchase> list = new ArrayList<>();
        FuelPurchase first = new FuelPurchase();
        first.setId(105);
        first.setDate(LocalDate.parse("2018-10-28"));
        first.setMileage(83623);
        first.setLitre(43.00999832);
        first.setPricePerLitre(1.43900001);
        first.setPrice(61.89138794);
        first.setLocation("Neste Express Nokia");
        first.setDescription("Dieseliä");
        first.setCredit(false);
        list.add(first);

        FuelPurchase second = new FuelPurchase();
        second.setId(106);
        second.setDate(LocalDate.parse("2018-11-08"));
        second.setMileage(84537);
        second.setLitre(59.0);
        second.setPricePerLitre(1.493999958);
        second.setPrice(88.14599609);
        second.setLocation("Neste Express Nokia");
        second.setDescription("Dieseliä");
        second.setCredit(false);
        list.add(second);

        FuelPurchase third = new FuelPurchase();
        third.setId(107);
        third.setDate(LocalDate.parse("2018-11-27"));
        third.setMileage(85374);
        third.setLitre(54.04999924);
        third.setPricePerLitre(1.458999991);
        third.setPrice(78.85894775);
        third.setLocation("Neste Express Nokia");
        third.setDescription("Dieseliä");
        third.setCredit(false);
        list.add(third);

        return list;
    }
}
