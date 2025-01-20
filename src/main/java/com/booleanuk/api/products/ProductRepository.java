package com.booleanuk.api.products;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

public class ProductRepository {
    private List<Product> products;

    public ProductRepository() {
        this.products = new ArrayList<>();
    }

    public Product createProduct(Product product) {
        for (Product sameNameProduct : this.products) {
            if (product.getName().equals(sameNameProduct.getName())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product with that name already exists");
            }
        }
        this.products.add(product);
        return product;
    }

    public List<Product> getAll(String category){
        if (category == null) {
            return this.products;
        }
        List<Product> matchingProducts = new ArrayList<>();
        for (Product product : this.products) {
            if (product.getCategory().equals(category)) {
                matchingProducts.add(product);
            }
        }
        if (matchingProducts.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found");
        }
        return matchingProducts;
    }


    public Product getOneProduct(int id) throws ResponseStatusException {
        for (Product product : this.products) {
            if (product.getId() == id) {
                return product;
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
    }

    public Product updateProduct(int id, Product product) throws ResponseStatusException {
        Product productToUpdate = this.getOneProduct(id);
        for (Product sameNameProduct : this.products) {
            if (product.getName().equals(sameNameProduct.getName())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product with that name already exists");
            }
        }
        productToUpdate.setName(product.getName());
        productToUpdate.setCategory(product.getCategory());
        productToUpdate.setPrice(product.getPrice());
        return productToUpdate;
    }


    public Product deleteProduct(int id) {
        Product productToDelete = this.getOneProduct(id);
        this.products.remove(productToDelete);
        return productToDelete;
    }

}
