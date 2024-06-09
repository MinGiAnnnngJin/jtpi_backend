package com.example.jtpi_backend.domain.dto;

public class PassDetailDTO {
    private Integer passId;
    private String imageUrl;
    private String transportType;
    private String title;
    private String routeInformation;
    private String price;
    private Integer period;
    private String productDescription;
    private String Map_Url;
    private String stationNames;
    private String break_even_usage;
    private String benefit_information;
    private String reservation_information;
    private String refund_information;

    public PassDetailDTO(Integer passId, String imageUrl, String transportType, String title, String routeInformation, String price, Integer period,String productDescription, String Map_Url, String stationNames, String  break_even_usage, String benefit_information, String reservation_information, String refund_information ) {
        this.passId = passId;
        this.imageUrl = imageUrl;
        this.transportType = transportType;
        this.title = title;
        this.routeInformation = routeInformation;
        this.price = price;
        this.period = period;
        this.productDescription=productDescription;
        this.Map_Url=Map_Url;
        this.stationNames=stationNames;
        this.break_even_usage=break_even_usage;
        this.benefit_information = benefit_information;
        this.reservation_information = reservation_information;
        this.refund_information = refund_information;

    }

    public Integer getPassId() {
        return passId;
    }
    public void setPassId(Integer passId) {
        this.passId = passId;
    }

    public String getImageUrl() {
        return imageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTransportType() {
        return transportType;
    }
    public void setTransportType(String transportType) {
        this.transportType = transportType;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getRouteInformation() {
        return routeInformation;
    }
    public void setRouteInformation(String routeInformation) {
        this.routeInformation = routeInformation;
    }

    public String getPrice() {
        return price;
    }
    public void setPrice(String price) {
        this.price = price;
    }

    public Integer getPeriod() {
        return period;
    }
    public void setPeriod(Integer period) {
        this.period = period;
    }

    public String getProductDescription(){return productDescription;}
    public void setProductDescription(String productDescription){this.productDescription=productDescription;}

    public String getBenefit_information() {
        return benefit_information;
    }
    public void setBenefit_information(String benefit_information) {
        this.benefit_information = benefit_information;
    }

    public String getReservation_information() {
        return reservation_information;
    }
    public void setReservation_information(String reservation_information) {
        this.reservation_information = reservation_information;
    }

    public String getRefund_information() {
        return refund_information;
    }
    public void setRefund_information(String refund_information) {
        this.refund_information = refund_information;
    }

    public String getMap_Url() {
        return Map_Url;
    }

    public void setMap_Url(String map_Url) {
        Map_Url = map_Url;
    }
    public String getStationNames() {
        return stationNames;
    }

    public void setStationNames(String stationNames) {
        this.stationNames = stationNames;
    }
    public String getBreak_even_usage() {
        return break_even_usage;
    }

    public void setBreak_even_usage(String break_even_usage) {
        this.break_even_usage = break_even_usage;
    }
    @Override
    public String toString() {
        return "PassDetailDTO{" +
                "paasId=" + passId +
                ", imageUrl='" + imageUrl + '\'' +
                ", transportType='" + transportType + '\'' +
                ", title='" + title + '\'' +
                ", routeInformation='" + routeInformation + '\'' +
                ", price=" + price + '\'' +
                ", period=" + period + '\'' +
                ", productDescription='" + productDescription + '\'' +
                ", Map_Url='" + Map_Url + '\'' +
                ", stationNames='" + stationNames + '\'' +
                ", break_even_usage='" + break_even_usage + '\'' +
                ", benefit_information='" + benefit_information + '\'' +
                ", reservation_information='" + reservation_information + '\'' +
                ", refund_information='" + refund_information + '\'' +
                '}';
    }



}

