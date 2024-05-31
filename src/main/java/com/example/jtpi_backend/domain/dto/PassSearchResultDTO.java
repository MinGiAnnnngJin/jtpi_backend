package com.example.jtpi_backend.domain.dto;

public class PassSearchResultDTO {
    private Integer passID;
    private String imageUrl;
    private String title;
    private String cityNames;
    private String price;

    public PassSearchResultDTO(){}
    public PassSearchResultDTO(Integer passID, String title, String imageUrl, String cityNames, String price){
        this.passID = passID;
        this.imageUrl = imageUrl;
        this.title = title;
        this.cityNames = cityNames;
        this.price = price;
    }
    public Integer getpassID() {
        return passID;
    }

    public void setpassID(Integer passiD) {
        this.passID = passiD;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCityNames() {
        return cityNames;
    }

    public void setCityNames(String cityNames) {
        this.cityNames = cityNames;
    }
}


