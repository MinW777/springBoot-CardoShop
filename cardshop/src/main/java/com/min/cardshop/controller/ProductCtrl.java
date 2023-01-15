package com.min.cardshop.controller;

import com.min.cardshop.constant.ProductCategory;
import com.min.cardshop.dto.ProductParam;
import com.min.cardshop.dto.ProductRequest;
import com.min.cardshop.model.Product;
import com.min.cardshop.service.ProductService;
import com.min.cardshop.util.Page;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@Validated
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
    public ResponseEntity<Page<Product>> getProducts(
            @RequestParam (required = false) ProductCategory category,
            @RequestParam (required = false) String keyword,
            @RequestParam (required = true ,defaultValue = "1") Integer sort,
            @RequestParam (defaultValue = "5") @Max(1000) @Min(0) Integer items,
            @RequestParam (defaultValue = "0") @Min(0) Integer offset
    ) {
        ProductParam productParam = new ProductParam();
        productParam.setCategory(category);
        productParam.setKeyword(keyword);
        productParam.setSort(sort);
        productParam.setItems(items);
        productParam.setOffset(offset);
        //使用參數處理資料
        List<Product> products = productService.getProducts(productParam);
        Integer total = productService.countProducts(productParam);
        //送出JSON obj
        Page<Product> page = new Page<>();
        page.setLimit(items);
        page.setOffset(offset);
        page.setTotal(total);
        page.setResult(products);
        return ResponseEntity.status(HttpStatus.OK).body(page);
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
