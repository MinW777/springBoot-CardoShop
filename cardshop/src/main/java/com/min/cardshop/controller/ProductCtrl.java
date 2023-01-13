package com.min.cardshop.controller;

import com.min.cardshop.constant.ProductCategory;
import com.min.cardshop.dto.ProductParam;
import com.min.cardshop.dto.ProductRequest;
import com.min.cardshop.model.Product;
import com.min.cardshop.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductCtrl {

    @Autowired
    private ProductService productService;

    @GetMapping("/prodcut/find/{productId}")
    public ResponseEntity<Product> getProduct(@PathVariable Integer productId) {
        Product product = productService.getProductById(productId);
        if ( product != null) {
            return ResponseEntity.status(HttpStatus.OK).body(product);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getProducts(
            @RequestParam (required = false) ProductCategory category,
            @RequestParam (required = false) String keyword
    ) {
        ProductParam productParam = new ProductParam();
        productParam.setCategory(category);
        productParam.setKeyword(keyword);

        List<Product> productList = productService.getProducts(productParam);
        return ResponseEntity.status(HttpStatus.OK).body(productList);
    }

    @PostMapping("/product/add")
    public ResponseEntity<ProductRequest> addProduct(@RequestBody @Valid ProductRequest request) {
        productService.addProduct(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(request);
    }

    @PutMapping("/product/edit/{productId}")
    public  ResponseEntity<Product> editProduct(@PathVariable Integer productId , @RequestBody ProductRequest request) {

        Product product = productService.getProductById(productId);
        if (product == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        productService.editProduct(productId,request);
        Product Updatedproduct = productService.getProductById(productId);
        return ResponseEntity.status(HttpStatus.OK).body(Updatedproduct);
    }

    @DeleteMapping("/product/delete/{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable Integer productId) {
        productService.deleteProductById(productId);
        return ResponseEntity.status(HttpStatus.OK).body("刪除成功");
    }
}
