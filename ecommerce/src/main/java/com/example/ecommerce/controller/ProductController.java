package com.example.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.example.ecommerce.entity.Product;
import com.example.ecommerce.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {

	
    @Autowired
    private ProductService service;
    
    
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return service.getProductById(id);
    }
    
    // GET ALL PRODUCTS
    @GetMapping("/all")
    public List<Product> getAllProducts() {
        return service.getAllProducts();
    }

    // ADD PRODUCT
       @PreAuthorize("hasAuthority('ADMIN')")
       @PostMapping("/add")
       public Product addProduct(@RequestBody Product product) {
           return service.addProduct(product);
       }
       
    // DELETE PRODUCT
    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        service.deleteProduct(id);
        return "Product deleted";
    }

    // UPDATE PRODUCT
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/update/{id}")
    public Product updateProduct(@PathVariable Long id,
                                 @RequestBody Product product) {
        return service.updateProduct(id, product);
    }
}