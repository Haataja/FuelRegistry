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
package fi.haataja.fuel.repository;

import fi.haataja.fuel.model.RawChart;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;


public interface FuelRepository extends CrudRepository<FuelPurchase, Long> {

    Iterable<FuelPurchase> findAllByOrderByIdAsc();

    Iterable<FuelPurchase> findAllByOrderByDateDesc();

    Iterable<FuelPurchase> findAllByDateAfterOrderByDateDesc(LocalDate refDate);

    @Query("select NEW fi.haataja.fuel.model.RawChart(MONTH(f.date), YEAR(f.date), sum(f.price)) from FuelPurchase AS f group by YEAR(f.date), MONTH(f.date) order by MONTH(f.date)")
    List<RawChart> dataForPriceChart();

    @Query("select NEW fi.haataja.fuel.model.RawChart(MONTH(f.date), YEAR(f.date), sum(f.price)) from FuelPurchase AS f where YEAR(f.date) > YEAR(CURDATE()) - ?1 group by YEAR(f.date), MONTH(f.date) order by MONTH(f.date)")
    List<RawChart> dataForPriceChart(int years);
}