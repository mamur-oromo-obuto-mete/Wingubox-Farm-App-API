// src/main/java/com/farm_api/farm_api/dto/ProductDto.java
package com.farm_api.farm_api.dto;

public class ProductDto {
    private Long id;
    private String name;
    private String category;
    private Double price;
    private Integer stock;
    private String farmerName;
    private Long farmerId; // ADD THIS FIELD

    // Getters and setters for all fields
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
    public String getFarmerName() {
        return farmerName;
    }
    public void setFarmerName(String farmerName) {
        this.farmerName = farmerName;
    }
    public Long getFarmerId() { // ADD THIS GETTER
        return farmerId;
    }
    public void setFarmerId(Long farmerId) { // ADD THIS SETTER
        this.farmerId = farmerId;
    }
}