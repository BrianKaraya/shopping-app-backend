package com.shoppingApp.shoppingApp_backend.service;

import com.shoppingApp.shoppingApp_backend.dao.InventoryDAO;
import com.shoppingApp.shoppingApp_backend.dao.ProductDAO;
import com.shoppingApp.shoppingApp_backend.model.Inventory;
import com.shoppingApp.shoppingApp_backend.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InventoryService {
    @Autowired
   public ProductDAO productDAO;
    @Autowired
    private InventoryDAO inventoryDAO;

    public InventoryService(InventoryDAO inventoryDAO,ProductDAO productDAO) {
        this.productDAO = productDAO;
        this.inventoryDAO = inventoryDAO;
    }

    public List<Inventory> getInventory(){
        return inventoryDAO.findAll();
    }

    public Optional<Inventory> getInventoryById(Long id){
        return inventoryDAO.findById(id);

    }
}
