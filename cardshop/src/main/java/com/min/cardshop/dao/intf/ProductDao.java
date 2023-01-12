package com.min.cardshop.dao.intf;

import com.min.cardshop.dto.ProductRequest;
import com.min.cardshop.model.Product;

public interface ProductDao {

    Product getProductById(Integer productId);

    void addProduct(ProductRequest request);

    void editProduct(Integer id,ProductRequest request);

    void deleteProductById(Integer id);
}
