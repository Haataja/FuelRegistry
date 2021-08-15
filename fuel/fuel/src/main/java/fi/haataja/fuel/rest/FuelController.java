/*
Copyright 2021 Hanna Haataja <hanna.haataja@kolumbus.fi>. All rights reserved.
Redistribution and use in source and binary forms, with or without modification, are permitted provided that the
following conditions are met:
1. Redistributions of source code must retain the above copyright notice, this list of conditions and the following
disclaimer.
2. Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following
disclaimer in the documentation and/or other materials provided with the distribution.
THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES,
INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/
package fi.haataja.fuel.rest;

import fi.haataja.fuel.ConvertUtil;
import fi.haataja.fuel.model.ChartResult;
import fi.haataja.fuel.model.RawChart;
import fi.haataja.fuel.repository.FuelPurchase;
import fi.haataja.fuel.repository.FuelRepository;
import fi.haataja.fuel.service.DataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class FuelController {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private DataService dataService;


    /**
     * Gets all the fuel purchases.
     *
     * @return 200 and database rows.
     */
    @RequestMapping("/purchases")
    public ResponseEntity<Iterable<FuelPurchase>> getPurchases(@RequestParam(required = false) String date) {
        if (date == null) {
            log.info("Requesting all rows");
            return dataService.findAll();
        } else {
            log.info("Requesting rows starting from {}", date);
            LocalDate localDate = LocalDate.parse(date);
            return dataService.findAllByDate(localDate);
        }
    }


    @RequestMapping("/purchases/{id}")
    public ResponseEntity<FuelPurchase> getPurchaseById(@PathVariable long id) {
        log.info("Requesting row with id {}", id);
        return dataService.findById(id);
    }

    @PostMapping("/purchases")
    public ResponseEntity<?> addPurchases(@RequestBody FuelPurchase fuelPurchase) {
        log.info("Adding rows for date {}", fuelPurchase.getDate());
        return dataService.save(fuelPurchase);
    }

    /**
     * Modifies the blog Purchases.
     *
     * @param purchase Modified Purchases.
     * @param id       the id of the Purchases that is modified.
     * @return Response entity.
     */
    @PostMapping("/purchases/{id}")
    public ResponseEntity<?> modifyPurchase(@RequestBody FuelPurchase purchase, @PathVariable long id) {
        log.info("Modifying rows for date {} and id {}", purchase.getDate(), id);

        return dataService.modify(id, purchase);
    }

    /**
     * Deletes the Purchase from the database.
     *
     * @param id the id of the Purchase that is deleted.
     * @return Response entity.
     */
    @DeleteMapping("/purchases/{id}")
    public ResponseEntity<?> deletePurchase(@PathVariable long id) {
        log.info("Deleting row with id {}", id);
        return dataService.delete(id);
    }

    @GetMapping("/chart/price")
    public ResponseEntity<?> getPriceChartData(){
        log.info("Getting the price chart data");
        return dataService.getPriceChartData(0);
    }

    @GetMapping("/chart/price/{years}")
    public ResponseEntity<?> getPriceChartData(@PathVariable int years){
        log.info("Getting the price chart data for last {} years", years);
        return dataService.getPriceChartData(years);
    }




}
