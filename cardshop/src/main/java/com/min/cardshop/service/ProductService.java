package com.min.cardshop.service;

import com.min.cardshop.dto.ProductParam;
import com.min.cardshop.dto.ProductRequest;
import com.min.cardshop.model.Product;

import java.util.List;

public interface ProductService {
    Product getProductById(Integer id);

    List<Product> getProducts(ProductParam productParam);

    void addProduct(ProductRequest productRequest);

    void editProduct(Integer id,ProductRequest request);

    void deleteProductById(Integer id);
}
