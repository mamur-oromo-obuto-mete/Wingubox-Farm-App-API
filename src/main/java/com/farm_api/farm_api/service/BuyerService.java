// src/main/java/com/farm_api/farm_api/service/BuyerService.java
package com.farm_api.farm_api.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.farm_api.farm_api.model.Buyer;
import com.farm_api.farm_api.repository.BuyerRepository;
import com.farm_api.farm_api.dto.BuyerDto;
import com.farm_api.farm_api.dto.BuyerRequestDto;

@Service
public class BuyerService {
    private final BuyerRepository buyerRepository;

    public BuyerService(BuyerRepository buyerRepository) {
        this.buyerRepository = buyerRepository;
    }

    public List<Buyer> getAllBuyers() {
        return buyerRepository.findAll();
    }

    public Optional<Buyer> getBuyerById(Long id) {
        return buyerRepository.findById(id);
    }

    public Buyer saveBuyer(Buyer buyer) {
        return buyerRepository.save(buyer);
    }

    public void deleteBuyer(Long id) {
        buyerRepository.deleteById(id);
    }

    // New conversion methods
    public Buyer convertToEntity(BuyerRequestDto buyerRequestDto) {
        Buyer buyer = new Buyer();
        buyer.setName(buyerRequestDto.getName());
        buyer.setLocation(buyerRequestDto.getLocation());
        buyer.setPhone(buyerRequestDto.getPhone());
        return buyer;
    }

    public BuyerDto convertToDto(Buyer buyer) {
        BuyerDto buyerDto = new BuyerDto();
        buyerDto.setId(buyer.getId());
        buyerDto.setName(buyer.getName());
        buyerDto.setLocation(buyer.getLocation());
        buyerDto.setPhone(buyer.getPhone());
        return buyerDto;
    }
}