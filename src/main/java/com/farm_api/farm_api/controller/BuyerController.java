// src/main/java/com/farm_api/farm_api/controller/BuyerController.java
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

import com.farm_api.farm_api.dto.BuyerDto;
import com.farm_api.farm_api.dto.BuyerRequestDto;
import com.farm_api.farm_api.model.Buyer;
import com.farm_api.farm_api.service.BuyerService;

@RestController
@RequestMapping("/buyers")
public class BuyerController {
    private final BuyerService buyerService;

    public BuyerController(BuyerService buyerService) {
        this.buyerService = buyerService;
    }

    // GET /buyers
    @GetMapping
    public List<BuyerDto> getAllBuyers() {
        return buyerService.getAllBuyers().stream()
                .map(buyerService::convertToDto)
                .collect(Collectors.toList());
    }

    // GET /buyers/{id}
    @GetMapping("/{id}")
    public ResponseEntity<BuyerDto> getBuyerById(@PathVariable Long id) {
        Optional<Buyer> buyer = buyerService.getBuyerById(id);
        return buyer.map(f -> ResponseEntity.ok(buyerService.convertToDto(f)))
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /buyers
    @PostMapping
    public ResponseEntity<BuyerDto> createBuyer(@RequestBody BuyerRequestDto buyerRequestDto) {
        Buyer buyer = buyerService.convertToEntity(buyerRequestDto);
        Buyer savedBuyer = buyerService.saveBuyer(buyer);
        return ResponseEntity.ok(buyerService.convertToDto(savedBuyer));
    }

    // PUT /buyers/{id}
    @PutMapping("/{id}")
    public ResponseEntity<BuyerDto> updateBuyer(@PathVariable Long id, @RequestBody BuyerRequestDto buyerRequestDto) {
        Optional<Buyer> existingBuyer = buyerService.getBuyerById(id);
        if (existingBuyer.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Buyer buyer = existingBuyer.get();
        buyer.setName(buyerRequestDto.getName());
        buyer.setLocation(buyerRequestDto.getLocation());
        buyer.setPhone(buyerRequestDto.getPhone());
        Buyer updated = buyerService.saveBuyer(buyer);
        return ResponseEntity.ok(buyerService.convertToDto(updated));
    }

    // DELETE /buyers/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBuyer(@PathVariable Long id) {
        Optional<Buyer> existingBuyer = buyerService.getBuyerById(id);
        if (existingBuyer.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        buyerService.deleteBuyer(id);
        return ResponseEntity.noContent().build();
    }
}