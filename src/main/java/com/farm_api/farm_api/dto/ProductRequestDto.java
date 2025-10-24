// src/main/java/com/farm_api/farm_api/dto/ProductRequestDto.java
package com.farm_api.farm_api.dto;

public class ProductRequestDto {
    private String name;
    private String category;
    private Double price;
    private Integer stock;
    private Long farmerId; // Field for the farmer's ID

    // Getters and setters for all fields
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
    public Long getFarmerId() {
        return farmerId;
    }
    public void setFarmerId(Long farmerId) {
        this.farmerId = farmerId;
    }
}