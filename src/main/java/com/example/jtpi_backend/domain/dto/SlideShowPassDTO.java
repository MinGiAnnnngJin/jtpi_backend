package com.example.jtpi_backend.domain.dto;
public class SlideShowPassDTO {
    private Integer id;
    private String title;
    private String imageUrl;

    public SlideShowPassDTO(){}
    public SlideShowPassDTO(Integer id, String title, String imageUrl ) {
        this.id = id;
        this.title = title;
        this.imageUrl = imageUrl;

    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    // Getters and Setters
}
