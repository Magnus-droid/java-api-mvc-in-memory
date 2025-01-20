package com.booleanuk.api.products;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("products")

public class ProductController {
    private ProductRepository theProducts;

    public ProductController() {
        this.theProducts = new ProductRepository();
    }

    @GetMapping
    public List<Product> getAll(@RequestParam(required = false) String category) {
        return this.theProducts.getAll(category);
    }

    @PostMapping
    public Product create(@RequestBody Product product) {
        return this.theProducts.createProduct(product);
    }

    @GetMapping("/{id}")
    public Product getOne(@PathVariable(name = "id") int id) {
        return this.theProducts.getOneProduct(id);
    }

    @PutMapping("/{id}")
    public Product update(@PathVariable(name = "id") int id, @RequestBody Product product) {
        return this.theProducts.updateProduct(id, product);
    }

    @DeleteMapping("/{id}")
    public Product delete(@PathVariable(name = "id") int id) {
        return this.theProducts.deleteProduct(id);
    }
}
