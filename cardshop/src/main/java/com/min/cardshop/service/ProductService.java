package com.min.cardshop.service;

import com.min.cardshop.dto.ProductRequest;
import com.min.cardshop.model.Product;

public interface ProductService {
    Product getProductById(Integer id);

    void addProduct(ProductRequest productRequest);

    void editProduct(Integer id,ProductRequest request);

    void deleteProductById(Integer id);
}
