package com.shoppingApp.shoppingApp_backend.service;

import com.shoppingApp.shoppingApp_backend.dao.ProductDAO;
import com.shoppingApp.shoppingApp_backend.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductDAO productDAO;

    public ProductService(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    public List<Product> getProducts(){
        return productDAO.findAll();
    }
    public Optional<Product> getProductById(Long id){
        return productDAO.findById(id);

    }
    public List<Product> addProduct(Product product){
        Product prodObj = productDAO.save(product);
        //return null;
        return List.of();
    }
//    public void updateProduct(Product product){
//        Optional<Product> oldProdData = productDAO.findById(product.getId());
//        if (oldProdData.isPresent()){
//            Product updatedProd = oldProdData.get();
//            updatedProd.setCategory();
//
//        }
//
//    }






}
