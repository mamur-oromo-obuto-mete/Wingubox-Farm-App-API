package com.farm_api.farm_api.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="sales")
public class Sale {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false)
    private Integer quantity;
    private LocalDateTime saleDate;

    // Relationships
    // Connection to product
    @ManyToOne
    @JoinColumn(name="product_id", nullable=false)
    private Product product;

    // Connection to buyer
    @ManyToOne
    @JoinColumn(name="buyer_id", nullable=false)
    private Buyer buyer;

    // Constructors
    public Sale () {}
    public Sale (Integer quantity, LocalDateTime saleDate, Product product, Buyer buyer) {
        this.quantity = quantity;
        this.saleDate = saleDate;
        this.product = product;
        this.buyer = buyer;
    }

    // Getters and setters
    // id
    public Long getId () {
        return id;
    }
    public void setId (Long id) {
        this.id = id;
    }

    // quantity
    public Integer getQuantity () {
        return quantity;
    }
    public void setQuantity (Integer quantity) {
        this.quantity = quantity;
    }

    // saleDate
    public LocalDateTime getSaleDate() {
        return saleDate;
    }
    public void setSaleDate (LocalDateTime saleDate) {
        this.saleDate = saleDate;
    }

    // product
    public Product getProduct () {
        return product;
    }
    public void setProduct (Product product) {
        this.product = product;
    }

    // buyer
    public Buyer getBuyer () {
        return buyer;
    }
    public void setBuyer (Buyer buyer) {
        this.buyer = buyer;
    }

}
