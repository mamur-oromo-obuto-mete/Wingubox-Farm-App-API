package com.farm_api.farm_api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.farm_api.farm_api.model.Sale;
import com.farm_api.farm_api.dto.SaleDto;
import com.farm_api.farm_api.repository.SaleRepository;

@Service
public class SaleService {
    private final SaleRepository saleRepository;

    // Constructor
    public SaleService(SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
    }

    // Get all sales
    public List<Sale> getAllSales() {
        return saleRepository.findAll();
    }

    // Get sale by ID
    public Optional<Sale> getSaleById(Long id) {
        return saleRepository.findById(id);
    }

    // Create or update sale
    public Sale saveSale(Sale sale) {
        return saleRepository.save(sale);
    }

    // Delete sale by ID
    public void deleteSale(Long id) {
        saleRepository.deleteById(id);
    }

    // Method to convert a Sale entity to a SaleDto
    public SaleDto convertToDto(Sale sale) {
        SaleDto dto = new SaleDto();
        dto.setId(sale.getId());
        dto.setQuantity(sale.getQuantity());
        dto.setSaleDate(sale.getSaleDate());

        if (sale.getProduct() != null) {
            dto.setProductId(sale.getProduct().getId());
            dto.setProductName(sale.getProduct().getName());
        }
        if (sale.getBuyer() != null) {
            dto.setBuyerId(sale.getBuyer().getId());
            dto.setBuyerName(sale.getBuyer().getName());
        }

        return dto;
    }
}