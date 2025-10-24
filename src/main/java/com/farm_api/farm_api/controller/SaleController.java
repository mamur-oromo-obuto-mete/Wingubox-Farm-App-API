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

import com.farm_api.farm_api.model.Buyer;
import com.farm_api.farm_api.model.Product;
import com.farm_api.farm_api.model.Sale;
import com.farm_api.farm_api.dto.SaleDto;
import com.farm_api.farm_api.dto.SaleRequestDto; // Import the new DTO
import com.farm_api.farm_api.service.BuyerService;
import com.farm_api.farm_api.service.ProductService;
import com.farm_api.farm_api.service.SaleService;

@RestController
@RequestMapping("/sales")
public class SaleController {
    private final SaleService saleService;
    private final ProductService productService;
    private final BuyerService buyerService;

    // Constructor
    public SaleController(SaleService saleService, ProductService productService, BuyerService buyerService) {
        this.saleService = saleService;
        this.productService = productService;
        this.buyerService = buyerService;
    }

    // GET /sales - list all sales
    @GetMapping
    public List<SaleDto> getAllSales() {
        List<Sale> sales = saleService.getAllSales();
        return sales.stream()
            .map(saleService::convertToDto)
            .collect(Collectors.toList());
    }

    // GET /sales/{id} - get one sale
    @GetMapping("/{id}")
    public ResponseEntity<SaleDto> getSaleById(@PathVariable Long id) {
        Optional<Sale> sale = saleService.getSaleById(id);
        if (sale.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        SaleDto dto = saleService.convertToDto(sale.get());
        return ResponseEntity.ok(dto);
    }

    // POST /sales - create new sale (updated to accept SaleRequestDto)
    @PostMapping
    public ResponseEntity<Sale> createSale(@RequestBody SaleRequestDto saleRequestDto) {
        Optional<Product> product = productService.getProductById(saleRequestDto.getProductId());
        Optional<Buyer> buyer = buyerService.getBuyerById(saleRequestDto.getBuyerId());

        if (product.isEmpty() || buyer.isEmpty()) {
            return ResponseEntity.badRequest().build(); // or a more specific error
        }

        Sale newSale = new Sale();
        newSale.setQuantity(saleRequestDto.getQuantity());
        newSale.setSaleDate(saleRequestDto.getSaleDate());
        newSale.setProduct(product.get());
        newSale.setBuyer(buyer.get());

        Sale savedSale = saleService.saveSale(newSale);
        return ResponseEntity.ok(savedSale);
    }

    // PUT /sales/{id} - update sale (updated to accept SaleRequestDto)
    @PutMapping("/{id}")
    public ResponseEntity<Sale> updateSale(@PathVariable Long id, @RequestBody SaleRequestDto saleRequestDto) {
        Optional<Sale> existingSale = saleService.getSaleById(id);
        
        if (existingSale.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Sale sale = existingSale.get();
        sale.setQuantity(saleRequestDto.getQuantity());
        sale.setSaleDate(saleRequestDto.getSaleDate());

        // Update product and buyer based on the new DTO
        Optional<Product> product = productService.getProductById(saleRequestDto.getProductId());
        Optional<Buyer> buyer = buyerService.getBuyerById(saleRequestDto.getBuyerId());
        
        if (product.isPresent()) {
            sale.setProduct(product.get());
        }
        if (buyer.isPresent()) {
            sale.setBuyer(buyer.get());
        }

        Sale updated = saleService.saveSale(sale);
        return ResponseEntity.ok(updated);
    }

    // DELETE /sales/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSale(@PathVariable Long id) {
        Optional<Sale> existingSale = saleService.getSaleById(id);

        if (existingSale.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        saleService.deleteSale(id);
        return ResponseEntity.noContent().build();
    }
}