package com.example.jtpi_backend.domain;

public class SearchParameters {
    private String searchQuery;
    private String departureCity;
    private String arrivalCity;
    private String transportType;
    private String routeInformation;
    private Integer duration;
    private Integer minPrice;
    private Integer maxPrice;


    @Override
    public String toString() {
        return "SearchParameters{" +
                "searchQuery='" + searchQuery + '\'' +
                ", departureCity='" + departureCity + '\'' +
                ", arrivalCity='" + arrivalCity + '\'' +
                ", transportType='" + transportType + '\'' +
                ", routeInformation='" + routeInformation + '\'' +
                ", duration='" + duration + '\'' +
                ", minPrice='" + minPrice + '\'' +
                ", maxPrice='" + maxPrice + '\'' +
                '}';
    }
    public String getsearchQuery() {

        return searchQuery;
    }

    public void setsearchQuery(String searchQuery) {
        this.searchQuery = searchQuery;
    }

    public String getTransportType() {
        return transportType;
    }

    public void setTransportType(String transportType) {
        this.transportType = transportType;
    }

    public String getRouteInformation() {
        return routeInformation;
    }

    public void setRouteInformation(String routeInformation) {
        this.routeInformation = routeInformation;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getArrivalCity() {
        return arrivalCity;
    }

    public void setArrivalCity(String arrivalCity) {
        this.arrivalCity = arrivalCity;
    }

    public String getDepartureCity() {
        return departureCity;
    }

    public void setDepartureCity(String departureCity) {
        this.departureCity = departureCity;
    }

    public Integer getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Integer minPrice) {
        this.minPrice = minPrice;
    }

    public Integer getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(Integer maxPrice) {
        this.maxPrice = maxPrice;
    }

    // Getters and Setters
}
