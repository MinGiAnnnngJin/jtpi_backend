package com.example.jtpi_backend.domain.entity;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "PassInformation")
public class PassInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer passID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "siteID", nullable = false)
    private SiteList site;

    @Column(length = 20, nullable = false)
    private String title;

    @Column(length = 100, nullable = false)
    private String description;

    @Column(nullable = false)
    private Integer price;

    @Column(nullable = false)
    private Integer period;

    @Column(length = 100, nullable = false)
    private String transportType;

    @Column(length = 50, nullable = false)
    private String cityNames;

    @Column(name = "create_at", nullable = false)
    private LocalDateTime createAt;

    @Column(name = "update_at", nullable = false)
    private LocalDateTime updateAt;

    @Column(length = 100, nullable = false)
    private String imageURL;

    @Column(nullable = false)
    private Integer quantityChildren = 0;

    @Column(nullable = false)
    private Integer quantityAdults = 0;
    // Constructors
    public PassInformation() {}

    public PassInformation(SiteList site, String title, String description, Integer price, Integer period, String transportType, String cityNames, LocalDateTime createAt, LocalDateTime updateAt, String imageURL) {
        this.site = site;
        this.title = title;
        this.description = description;
        this.price = price;
        this.period = period;
        this.transportType = transportType;
        this.cityNames = cityNames;
        this.createAt = createAt;
        this.updateAt = updateAt;
        this.imageURL = imageURL;
    }

    // Getters and Setters
    public Integer getpassID() {
        return passID;
    }

    public void setpassID(Integer passId) {
        this.passID = passId;
    }

    public SiteList getSite() {
        return site;
    }

    public void setSite(SiteList site) {
        this.site = site;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public LocalDateTime getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(LocalDateTime updateAt) {
        this.updateAt = updateAt;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
    public Integer getQuantityChildren() {
        return quantityChildren;
    }

    public void setQuantityChildren(Integer quantityChildren) {
        this.quantityChildren = quantityChildren;
    }

    public Integer getQuantityAdults() {
        return quantityAdults;
    }

    public void setQuantityAdults(Integer quantityAdults) {
        this.quantityAdults = quantityAdults;
    }
}
