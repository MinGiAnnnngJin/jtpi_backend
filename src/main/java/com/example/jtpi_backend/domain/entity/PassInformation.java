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

    @Column(nullable = false)
    private String price;

    @Column(nullable = false)
    private Integer period;

    @Column(length = 100, nullable = false)
    private String transportType;

    @Column(length = 50, nullable = false)
    private String routeInformation;

    @Column(name = "create_at", nullable = false)
    private LocalDateTime createAt;

    @Column(name = "update_at", nullable = false)
    private LocalDateTime updateAt;

    @Column(length = 100, nullable = false)
    private String imageURL;
    private String benefitInformation;
    private String reservationInformation;
    private String refundInformation;
    private String productDescription;
    private String stationNames;
    private String Map_Url;
    private String break_even_usage;

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

    public String getBenefitInformation() {
        return benefitInformation;
    }

    public void setBenefitInformation(String benefitInformation) {
        this.benefitInformation = benefitInformation;
    }

    public String getReservationInformation() {
        return reservationInformation;
    }

    public void setReservationInformation(String reservationInformation) {
        this.reservationInformation = reservationInformation;
    }

    public String getRefundInformation() {
        return refundInformation;
    }

    public void setRefundInformation(String refundInformation) {
        this.refundInformation = refundInformation;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public String getStationNames() {
        return stationNames;
    }

    public void setStationNames(String stationNames) {
        this.stationNames = stationNames;
    }

    public String getMap_Url() {
        return Map_Url;
    }

    public void setMap_Url(String map_Url) {
        Map_Url = map_Url;
    }

    public String getBreak_even_usage() {
        return break_even_usage;
    }

    public void setBreak_even_usage(String break_even_usage) {
        this.break_even_usage = break_even_usage;
    }
}
