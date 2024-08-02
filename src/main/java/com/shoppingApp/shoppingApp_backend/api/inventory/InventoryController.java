package com.shoppingApp.shoppingApp_backend.api.inventory;

import com.shoppingApp.shoppingApp_backend.model.Inventory;
import com.shoppingApp.shoppingApp_backend.model.Product;
import com.shoppingApp.shoppingApp_backend.service.InventoryService;
import com.shoppingApp.shoppingApp_backend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/inventory")
public class InventoryController {
    @Autowired
    private ProductService productService;
    @Autowired
    private InventoryService inventoryService;

    public InventoryController(ProductService productService, InventoryService inventoryService) {
        this.productService = productService;
        this.inventoryService = inventoryService;
    }

    @GetMapping
    public List<Inventory> getInventory() {
        return inventoryService.getInventory();

    }
}
