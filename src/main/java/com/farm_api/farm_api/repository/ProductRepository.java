package com.farm_api.farm_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.farm_api.farm_api.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    // CRUD methods are for free
    // findAll(), findById(), save(), deleteById() etc...
    // Customization can be made to queries e.g.:
}
