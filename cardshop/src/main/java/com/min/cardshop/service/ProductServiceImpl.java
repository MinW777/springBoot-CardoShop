package com.min.cardshop.service;

import com.min.cardshop.dao.intf.ProductDao;
import com.min.cardshop.dto.ProductRequest;
import com.min.cardshop.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductDao productDao;

    @Override
    public Product getProductById(Integer id) {
        return productDao.getProductById(id);
    }

    @Override
    public void addProduct(ProductRequest productRequest) {
        productDao.addProduct(productRequest);
    }

    @Override
    public void editProduct(Integer id, ProductRequest request) {
        productDao.editProduct(id,request);
    }

    @Override
    public void deleteProductById(Integer id) {
        productDao.deleteProductById(id);
    }
}
