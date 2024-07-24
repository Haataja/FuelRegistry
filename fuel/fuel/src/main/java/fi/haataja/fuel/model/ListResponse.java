package fi.haataja.fuel.model;

import fi.haataja.fuel.repository.FuelPurchase;

import java.util.List;

public class ListResponse {
    private Integer size;
    private List<FuelPurchase> data;

    public ListResponse() {
    }

    public ListResponse(Integer size, List<FuelPurchase> data) {
        this.size = size;
        this.data = data;
    }
    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public List<FuelPurchase> getData() {
        return data;
    }

    public void setData(List<FuelPurchase> data) {
        this.data = data;
    }
}
