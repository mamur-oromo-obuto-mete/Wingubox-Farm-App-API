package com.farm_api.farm_api.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name="products")
public class Product {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false)
    private String name;
    private String category;
    private Double price;
    private Integer stock;

    // Relationship to farmer
    @ManyToOne
    @JoinColumn(name="farmer_id", nullable=false)
    private Farmer farmer;

    // A transient field for handling requests from the client
    @Transient
    private Long farmerId;

    // Constructors
    public Product(){}

    public Product(String name, String category, Double price, Integer stock, Farmer farmer) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.stock = stock;
        this.farmer = farmer;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }

    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }
    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Farmer getFarmer() {
        return farmer;
    }
    public void setFarmer(Farmer farmer) {
        this.farmer = farmer;
    }

    // Custom getters and setters for the transient field
    public Long getFarmerId() {
        return farmer != null ? farmer.getId() : null;
    }

    public void setFarmerId(Long farmerId) {
        this.farmerId = farmerId;
    }
}