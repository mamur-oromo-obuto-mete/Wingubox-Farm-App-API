// src/main/java/com/farm_api/farm_api/controller/ProductController.java
package com.farm_api.farm_api.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.farm_api.farm_api.model.Farmer;
import com.farm_api.farm_api.model.Product;
import com.farm_api.farm_api.dto.ProductDto; // Import the new DTOs
import com.farm_api.farm_api.dto.ProductRequestDto;
import com.farm_api.farm_api.service.FarmerService;
import com.farm_api.farm_api.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;
    private final FarmerService farmerService;

    public ProductController(ProductService productService, FarmerService farmerService) {
        this.productService = productService;
        this.farmerService = farmerService;
    }

    // GET /products - list all products
    @GetMapping
    public List<ProductDto> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return products.stream()
                .map(productService::convertToDto)
                .collect(Collectors.toList());
    }

    // GET /products/{id} - get one product
    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable Long id) {
        Optional<Product> product = productService.getProductById(id);
        if (product.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        ProductDto dto = productService.convertToDto(product.get());
        return ResponseEntity.ok(dto);
    }

    // POST /products - create new product (using ProductRequestDto)
    @PostMapping
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductRequestDto productRequestDto) {
        Optional<Farmer> farmerOptional = farmerService.getFarmerById(productRequestDto.getFarmerId());
        
        if (farmerOptional.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        Product newProduct = new Product();
        newProduct.setName(productRequestDto.getName());
        newProduct.setCategory(productRequestDto.getCategory());
        newProduct.setPrice(productRequestDto.getPrice());
        newProduct.setStock(productRequestDto.getStock());
        newProduct.setFarmer(farmerOptional.get());

        Product savedProduct = productService.saveProduct(newProduct);
        return ResponseEntity.ok(productService.convertToDto(savedProduct));
    }

    // PUT /products/{id} - update product (using ProductRequestDto)
    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable Long id, @RequestBody ProductRequestDto productRequestDto) {
        Optional<Product> existingProductOptional = productService.getProductById(id);
        
        if (existingProductOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Product product = existingProductOptional.get();
        product.setName(productRequestDto.getName());
        product.setCategory(productRequestDto.getCategory());
        product.setPrice(productRequestDto.getPrice());
        product.setStock(productRequestDto.getStock());

        // Update farmer if provided
        Optional<Farmer> farmerOptional = farmerService.getFarmerById(productRequestDto.getFarmerId());
        if (farmerOptional.isPresent()) {
            product.setFarmer(farmerOptional.get());
        }

        Product updatedProduct = productService.saveProduct(product);
        return ResponseEntity.ok(productService.convertToDto(updatedProduct));
    }

    // DELETE /products/{id} - delete product
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        Optional<Product> existingProduct = productService.getProductById(id);
        if (existingProduct.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}