// src/main/java/com/farm_api/farm_api/service/ProductService.java
package com.farm_api.farm_api.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.farm_api.farm_api.model.Product;
import com.farm_api.farm_api.repository.ProductRepository;
import com.farm_api.farm_api.dto.ProductDto;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    // Method to convert a Product entity to a ProductDto
    public ProductDto convertToDto(Product product) {
        ProductDto dto = new ProductDto();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setCategory(product.getCategory());
        dto.setPrice(product.getPrice());
        dto.setStock(product.getStock());
        
        if (product.getFarmer() != null) {
            dto.setFarmerName(product.getFarmer().getName());
            dto.setFarmerId(product.getFarmer().getId()); // ADD THIS LINE
        }
        return dto;
    }
}