package com.farm_api.farm_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.farm_api.farm_api.model.Sale;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {
    // CRUD methods are for free
    // findAll(), findById(), save(), deleteById() etc...
    // Customization can be made to queries e.g.:
}
