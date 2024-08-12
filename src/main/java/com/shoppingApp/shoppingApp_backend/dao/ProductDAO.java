package com.shoppingApp.shoppingApp_backend.dao;

import com.shoppingApp.shoppingApp_backend.model.Product;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductDAO extends ListCrudRepository<Product,Long> {
    @Override
    Optional<Product> findById(Long aLong);
    void deleteById(Long Long);
}
