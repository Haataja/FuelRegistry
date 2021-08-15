package fi.haataja.fuel.model;

public class RawChart {
    private int month;
    private int year;
    private double value;



    public RawChart(int month, int year, double value) {
        this.month = month;
        this.year = year;
        this.value = value;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
