package com.example.jtpi_backend.domain.dto;

import javax.swing.*;

public class PassSearchResultDTO {
    private Integer passID;
    private String imageUrl;
    private String title;
    private String routeInformation;
    private String price;

    public PassSearchResultDTO(){}
    public PassSearchResultDTO(Integer passID, String title, String imageUrl, String  routeInformation, String price){
        this.passID = passID;
        this.imageUrl = imageUrl;
        this.title = title;
        this.routeInformation =  routeInformation;
        this.price = price;
    }
    @Override
    public String toString() {
        return "PassSearchResultDTO{" +
                "id=" + passID +
                ", imageUrl='" + imageUrl + '\'' +
                ", title='" + title + '\'' +
                ", routeInformation='" +  routeInformation + '\'' +
                ", price='" + price + '\'' +

                '}';
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

    public String  getRouteInformation() {
        return  routeInformation;
    }

    public void  setRouteInformation(String  routeInformation) {
        this. routeInformation =  routeInformation;
    }
}


