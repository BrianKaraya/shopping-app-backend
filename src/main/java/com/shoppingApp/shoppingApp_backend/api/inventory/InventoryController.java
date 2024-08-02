package com.shoppingApp.shoppingApp_backend.api.inventory;

import com.shoppingApp.shoppingApp_backend.model.Inventory;
import com.shoppingApp.shoppingApp_backend.service.InventoryService;
import com.shoppingApp.shoppingApp_backend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping
public class InventoryController {
    @Autowired
    private ProductService productService;
    @Autowired
    private InventoryService inventoryService;

    public InventoryController(ProductService productService, InventoryService inventoryService) {
        this.productService = productService;
        this.inventoryService = inventoryService;
    }

    @GetMapping("/inventory")
    public List<Inventory> getInventory() {
        return inventoryService.getInventory();

    }
    @GetMapping("/inventory/{id}")
    public Optional<Inventory> getInventoryById(@PathVariable Long id) {
        return inventoryService.getInventoryById(id);
    }
}
