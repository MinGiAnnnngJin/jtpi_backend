package com.example.jtpi_backend.domain;
public class SearchParameters {
    private String query;
    private String departureCity;
    private String arrivalCity;
    private String transportType;
    private String cityNames;
    private Integer duration;
    private Integer quantityAdults;
    private Integer quantityChildren;

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getDepartureCity() {
        return departureCity;
    }

    public void setDepartureCity(String departureCity) {
        this.departureCity = departureCity;
    }

    public String getArrivalCity() {
        return arrivalCity;
    }

    public void setArrivalCity(String arrivalCity) {
        this.arrivalCity = arrivalCity;
    }

    public String getTransportType() {
        return transportType;
    }

    public void setTransportType(String transportType) {
        this.transportType = transportType;
    }

    public String getCityNames() {
        return cityNames;
    }

    public void setCityNames(String cityNames) {
        this.cityNames = cityNames;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getQuantityAdults() {
        return quantityAdults;
    }

    public void setQuantityAdults(Integer quantityAdults) {
        this.quantityAdults = quantityAdults;
    }

    public Integer getQuantityChildren() {
        return quantityChildren;
    }

    public void setQuantityChildren(Integer quantityChildren) {
        this.quantityChildren = quantityChildren;
    }

    // Getters and Setters
}
