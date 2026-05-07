package com.example.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ecommerce.entity.Product;
import com.example.ecommerce.repository.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public Product getProductById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }
    
    // ADD PRODUCT
    public Product addProduct(Product product) {
        return repository.save(product);
    }

    // GET ALL PRODUCTS
    public List<Product> getAllProducts() {
        return repository.findAll();
    }

    // DELETE PRODUCT
    public void deleteProduct(Long id) {
        repository.deleteById(id);
    }

    // UPDATE PRODUCT
    public Product updateProduct(Long id, Product product) {

        Product existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        existing.setName(product.getName());
        existing.setPrice(product.getPrice());
        existing.setDescription(product.getDescription());

        return repository.save(existing);
    }
}