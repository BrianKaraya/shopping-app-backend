package com.shoppingApp.shoppingApp_backend.api.product;

import com.shoppingApp.shoppingApp_backend.dao.ProductDAO;
import com.shoppingApp.shoppingApp_backend.model.Product;
import com.shoppingApp.shoppingApp_backend.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductDAO productDAO;
    private ProductService productService;

    public ProductController(ProductService productService, ProductDAO productDAO) {
        this.productService = productService;
        this.productDAO = productDAO;
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

    @PostMapping("/updateById/{id}")
    public ResponseEntity<Product> updateProductById(@PathVariable Long id, @RequestBody Product newProductData) {
        Optional<Product> oldProductData = productDAO.findById(id);

        if (oldProductData.isPresent()) {
            Product updatedProduct = oldProductData.get();
            updatedProduct.setName(newProductData.getName());
            updatedProduct.setDescription(newProductData.getDescription());
            updatedProduct.setPrice(newProductData.getPrice());
            updatedProduct.setCategory(newProductData.getCategory());

            Product prodObj = productDAO.save(updatedProduct);
            return new ResponseEntity<>(prodObj, HttpStatus.OK);


        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    //Foreign key constraint fails
    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<HttpStatus> deleteProductById(@PathVariable Long id) {
        productDAO.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);


    }


}
