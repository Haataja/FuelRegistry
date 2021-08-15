package fi.haataja.fuel.model;

import java.util.ArrayList;
import java.util.List;

public class ChartResult {
    private String name;
    private List<SeriesItem> series;

    public ChartResult() {
        series = new ArrayList<>();
    }

    public ChartResult(String name, String itemName, double value) {
        this.name = name;
        series = new ArrayList<>();
        series.add(new SeriesItem(itemName, value));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SeriesItem> getSeries() {
        return series;
    }

    public void setSeries(List<SeriesItem> series) {
        this.series = series;
    }

    public static class SeriesItem{
        private String name;
        private double value;

        public SeriesItem(String name, double value) {
            this.name = name;
            this.value = value;
        }

        public SeriesItem() {
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public double getValue() {
            return value;
        }

        public void setValue(double value) {
            this.value = value;
        }
    }

}
