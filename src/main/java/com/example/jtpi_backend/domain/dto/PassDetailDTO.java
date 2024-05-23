package com.example.jtpi_backend.domain.dto;

public class PassDetailDTO {
    private Integer passId;
    private String imageUrl;
    private String transportType;
    private String title;
    private String cityNames;
    private Integer price;
    private Integer period;
    private String productDescription;
    private String benefit_information;
    private String reservation_information;
    private String refund_information;

    public PassDetailDTO(Integer passId, String imageUrl, String transportType, String title, String cityNames, Integer price, Integer period,String productDescription, String benefit_information, String reservation_information, String refund_information ) {
        this.passId = passId;
        this.imageUrl = imageUrl;
        this.transportType = transportType;
        this.title = title;
        this.cityNames = cityNames;
        this.price = price;
        this.period = period;
        this.productDescription=productDescription;
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

    public String getCityNames() {
        return cityNames;
    }
    public void setCityNames(String cityNames) {
        this.cityNames = cityNames;
    }

    public Integer getPrice() {
        return price;
    }
    public void setPrice(Integer price) {
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

    @Override
    public String toString() {
        return "PassDetailDTO{" +
                "paasId=" + passId +
                ", imageUrl='" + imageUrl + '\'' +
                ", transportType='" + transportType + '\'' +
                ", title='" + title + '\'' +
                ", cityNames='" + cityNames + '\'' +
                ", price=" + price + '\'' +
                ", period=" + period + '\'' +
                ", productDescription='" + productDescription + '\'' +
                ", benefit_information='" + benefit_information + '\'' +
                ", reservation_information='" + reservation_information + '\'' +
                ", refund_information='" + refund_information + '\'' +
                '}';
    }
}

