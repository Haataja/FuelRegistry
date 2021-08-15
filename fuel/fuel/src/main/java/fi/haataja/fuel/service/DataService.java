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
package fi.haataja.fuel.service;

import fi.haataja.fuel.ConvertUtil;
import fi.haataja.fuel.model.ChartResult;
import fi.haataja.fuel.model.RawChart;
import fi.haataja.fuel.repository.FuelPurchase;
import fi.haataja.fuel.repository.FuelRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class DataService {
    Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private FuelRepository fuelRepository;

    public ResponseEntity<Iterable<FuelPurchase>> findAll(){
        return new ResponseEntity<>(fuelRepository.findAllByOrderByDateDesc(), HttpStatus.OK);
    }

    public ResponseEntity<Iterable<FuelPurchase>> findAllByDate(LocalDate localDate){
        return new ResponseEntity<>(fuelRepository.findAllByDateAfterOrderByDateDesc(localDate), HttpStatus.OK);
    }

    public ResponseEntity<FuelPurchase> findById(long id){
        log.info("Requesting row with id {}", id);
        Optional<FuelPurchase> purchase = fuelRepository.findById(id);
        if (purchase.isPresent()) {
            log.info("Row was found");
            return new ResponseEntity<>(purchase.get(), HttpStatus.OK);
        } else {
            log.info("Row was not found");
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<?> save(FuelPurchase fuelPurchase){
        return new ResponseEntity<>(fuelRepository.save(fuelPurchase), HttpStatus.CREATED);
    }

    public ResponseEntity<?> modify(long id, FuelPurchase purchase) {
        Optional<FuelPurchase> purchaseOpt = fuelRepository.findById(id);
        if (purchaseOpt.isPresent()) {
            FuelPurchase existing = purchaseOpt.get();
            existing.setDate(purchase.getDate());
            existing.setDescription(purchase.getDescription());
            existing.setCredit(purchase.isCredit());
            existing.setLitre(purchase.getLitre());
            existing.setLocation(purchase.getLocation());
            existing.setMileage(purchase.getMileage());
            existing.setPrice(purchase.getPrice());
            existing.setPricePerLitre(purchase.getPricePerLitre());
            return save(existing);
        } else {
            return new ResponseEntity<>(purchase, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<?> delete(long id) {
        Optional<FuelPurchase> purchaseOpt = fuelRepository.findById(id);
        if (purchaseOpt.isPresent()) {
            fuelRepository.deleteById(id);
            return new ResponseEntity<>("{\"success\":\"ok\"}", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("{\"success\":\"error\"}", HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<?> getPriceChartData(int years) {
        List<RawChart> rawData;
        if(years == 0){
            rawData = fuelRepository.dataForPriceChart();
        } else {
            rawData = fuelRepository.dataForPriceChart(years);
        }
        List<ChartResult> results = new ArrayList<>();
        for(RawChart r: rawData) {
            boolean newValue = true;
            for(ChartResult c: results){
                if(c.getName().equalsIgnoreCase(ConvertUtil.getMonth(r.getMonth()))){
                    c.getSeries().add(new ChartResult.SeriesItem(String.valueOf(r.getYear()), r.getValue()));
                    newValue = false;
                    break;
                }
            }

            if(newValue){
                results.add(new ChartResult(ConvertUtil.getMonth(r.getMonth()), String.valueOf(r.getYear()), r.getValue()));
            }
        }
        return new ResponseEntity<>(results, HttpStatus.OK);
    }
}
