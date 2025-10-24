// src/main/java/com/farm_api/farm_api/controller/FarmerController.java
package com.farm_api.farm_api.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.farm_api.farm_api.dto.FarmerDto;
import com.farm_api.farm_api.dto.FarmerRequestDto;
import com.farm_api.farm_api.model.Farmer;
import com.farm_api.farm_api.service.FarmerService;

@RestController
@RequestMapping("/farmers")
public class FarmerController {
    private final FarmerService farmerService;

    public FarmerController(FarmerService farmerService) {
        this.farmerService = farmerService;
    }

    // GET /farmers
    @GetMapping
    public List<FarmerDto> getAllFarmers() {
        return farmerService.getAllFarmers().stream()
                .map(farmerService::convertToDto)
                .collect(Collectors.toList());
    }

    // GET /farmers/{id}
    @GetMapping("/{id}")
    public ResponseEntity<FarmerDto> getFarmerById(@PathVariable Long id) {
        Optional<Farmer> farmer = farmerService.getFarmerById(id);
        return farmer.map(f -> ResponseEntity.ok(farmerService.convertToDto(f)))
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /farmers
    @PostMapping
    public ResponseEntity<FarmerDto> createFarmer(@RequestBody FarmerRequestDto farmerRequestDto) {
        Farmer farmer = farmerService.convertToEntity(farmerRequestDto);
        Farmer savedFarmer = farmerService.saveFarmer(farmer);
        return ResponseEntity.ok(farmerService.convertToDto(savedFarmer));
    }

    // PUT /farmers/{id}
    @PutMapping("/{id}")
    public ResponseEntity<FarmerDto> updateFarmer(@PathVariable Long id, @RequestBody FarmerRequestDto farmerRequestDto) {
        Optional<Farmer> existingFarmer = farmerService.getFarmerById(id);
        if (existingFarmer.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Farmer farmer = existingFarmer.get();
        farmer.setName(farmerRequestDto.getName());
        farmer.setLocation(farmerRequestDto.getLocation());
        farmer.setPhone(farmerRequestDto.getPhone());
        Farmer updatedFarmer = farmerService.saveFarmer(farmer);
        return ResponseEntity.ok(farmerService.convertToDto(updatedFarmer));
    }

    // DELETE /farmers/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFarmer(@PathVariable Long id) {
        Optional<Farmer> existingFarmer = farmerService.getFarmerById(id);
        if (existingFarmer.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        farmerService.deleteFarmer(id);
        return ResponseEntity.noContent().build();
    }
}