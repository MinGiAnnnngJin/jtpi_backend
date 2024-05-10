package com.example.jtpi_backend.domain.dto;

public class PassSearchResultDTO {
    private Integer passID;
    private String imageUrl;
    private String title;
    private Integer price;

    // 기본 생성자
    public PassSearchResultDTO() {
    }

    // 모든 필드를 포함하는 생성자
    public PassSearchResultDTO(Integer passID, String imageUrl, String title, Integer price) {
        this.passID = passID;
        this.imageUrl = imageUrl;
        this.title = title;
        this.price = price;
    }

    public Integer getpassID() {
        return passID;
    }

    public void setpassID(Integer passID) {
        this.passID = passID;
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

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
