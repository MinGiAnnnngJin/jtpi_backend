package com.example.jtpi_backend.domain.entity;
import jakarta.persistence.*;

@Entity
@Table(name = "SiteList")
public class SiteList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer siteId;

    @Column(length = 20, nullable = false)
    private String siteName;

    @Column(length = 100, nullable = false)
    private String url;

    // Constructors
    public SiteList() {}

    public SiteList(String siteName, String url) {
        this.siteName = siteName;
        this.url = url;
    }
    public void setSiteID(Integer siteId) {
        this.siteId = siteId;
    }
    public Integer getSiteID() {
        return siteId;
    }
    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }
    public String getSiteName() {
        return siteName;
    }
    public void seturl(String url) {
        this.url = url;
    }
    public String geturl() {
        return url;
    }




}
