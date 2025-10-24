// src/main/java/com/farm_api/farm_api/service/FarmerService.java
package com.farm_api.farm_api.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.farm_api.farm_api.model.Farmer;
import com.farm_api.farm_api.repository.FarmerRepository;
import com.farm_api.farm_api.dto.FarmerDto;
import com.farm_api.farm_api.dto.FarmerRequestDto;

@Service
public class FarmerService {
    private final FarmerRepository farmerRepository;

    public FarmerService(FarmerRepository farmerRepository) {
        this.farmerRepository = farmerRepository;
    }

    public List<Farmer> getAllFarmers() {
        return farmerRepository.findAll();
    }

    public Optional<Farmer> getFarmerById(Long id) {
        return farmerRepository.findById(id);
    }

    public Farmer saveFarmer(Farmer farmer) {
        return farmerRepository.save(farmer);
    }

    public void deleteFarmer(Long id) {
        farmerRepository.deleteById(id);
    }

    // New conversion methods
    public Farmer convertToEntity(FarmerRequestDto farmerRequestDto) {
        Farmer farmer = new Farmer();
        farmer.setName(farmerRequestDto.getName());
        farmer.setLocation(farmerRequestDto.getLocation());
        farmer.setPhone(farmerRequestDto.getPhone());
        return farmer;
    }

    public FarmerDto convertToDto(Farmer farmer) {
        FarmerDto farmerDto = new FarmerDto();
        farmerDto.setId(farmer.getId());
        farmerDto.setName(farmer.getName());
        farmerDto.setLocation(farmer.getLocation());
        farmerDto.setPhone(farmer.getPhone());
        return farmerDto;
    }
}