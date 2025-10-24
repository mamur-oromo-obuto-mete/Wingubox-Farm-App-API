// src/main/java/com/farm_api/farm_api/dto/BuyerRequestDto.java
package com.farm_api.farm_api.dto;

public class BuyerRequestDto {
    private String name;
    private String location;
    private String phone;

    // Getters and Setters
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
}