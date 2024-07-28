package com.shoppingApp.shoppingApp_backend.api.product;

import com.shoppingApp.shoppingApp_backend.model.Product;
import com.shoppingApp.shoppingApp_backend.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getProducts() {
        return productService.getProducts();
    }

    @GetMapping("/{id}")
    public Optional<Product> getProductById(@PathVariable Long id) {
        return productService.getProductById(id);

    }

    @PostMapping("/add")
    public List<Product> addProduct(@RequestBody Product product) {
        return productService.addProduct(product);

    }
    //Foreign key constraint fails
//    @DeleteMapping("/deleteById/{id}"){}


}
