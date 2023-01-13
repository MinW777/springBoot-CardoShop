package com.min.cardshop.dao.intf;

import com.min.cardshop.dto.ProductParam;
import com.min.cardshop.dto.ProductRequest;
import com.min.cardshop.model.Product;

import java.util.List;

public interface ProductDao {

    Product getProductById(Integer productId);

    List<Product> getProducts(ProductParam productParam);

    void addProduct(ProductRequest request);

    void editProduct(Integer id,ProductRequest request);

    void deleteProductById(Integer id);
}
