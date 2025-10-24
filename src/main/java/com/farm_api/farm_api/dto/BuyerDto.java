// src/main/java/com/farm_api/farm_api/dto/BuyerDto.java
package com.farm_api.farm_api.dto;

public class BuyerDto {
    private Long id;
    private String name;
    private String location;
    private String phone;

    // Getters and Setters
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