package com.min.cardshop.dao.impl;

import com.min.cardshop.dao.intf.ProductDao;
import com.min.cardshop.dto.ProductRequest;
import com.min.cardshop.model.Product;
import com.min.cardshop.rowMapper.ProductRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.fasterxml.jackson.databind.type.LogicalType.Map;

@Component
public class ProductDaoImpl implements ProductDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Product getProductById(Integer productId) {
        //寫JDBC
        String sql = "SELECT  product_id, product_name, category, image_url, price, stock, description, created_date, last_modified_date FROM product WHERE product_id = :productId";

        Map<String,Object> map = new HashMap<>();
        map.put("productId" , productId);

        List<Product> productslist = namedParameterJdbcTemplate.query(sql, map, new ProductRowMapper());

        if (productslist.size() > 0) {
            return productslist.get(0);
        } else {
            return null;
        }
    }

    @Override
    public void addProduct(ProductRequest request) {
        String sql = "INSERT INTO product (product_name, category, image_url, price, stock, description, created_date, last_modified_date) " +
                "VALUES (:productName, :category, :imageUrl, :price, :stock, :description, :createdDate, :lastModifiedDate)";

        Map<String, Object>map = new HashMap<>();
        map.put("productName" , request.getProductName());
        map.put("category" , request.getCategory().toString());
        map.put("imageUrl" , request.getImageUrl());
        map.put("price" , request.getPrice());
        map.put("stock" , request.getStock());
        map.put("description" , request.getDescription());

        Date now = new Date();
        map.put("createdDate" , now);
        map.put("lastModifiedDate" , now);

        KeyHolder keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map),keyHolder);
    }

    @Override
    public void editProduct(Integer id, ProductRequest request) {
        String sql = "UPDATE product SET `product_name`= :productName, `category`= :category, `image_url`= :imageUrl, `price`= :price, `stock`= :stock, `description`= :description, `last_modified_date`= :lastModifiedDate WHERE `product_id` = :productId";
        Map<String, Object>map = new HashMap<>();
        map.put("productId", id);

        map.put("productName" , request.getProductName());
        map.put("category" , request.getCategory().toString());
        map.put("imageUrl" , request.getImageUrl());
        map.put("price" , request.getPrice());
        map.put("stock" , request.getStock());
        map.put("description" , request.getDescription());

        map.put("lastModifiedDate" , new Date());

        namedParameterJdbcTemplate.update(sql, map);
    }

    @Override
    public void deleteProductById(Integer id) {
        String sql = "DELETE FROM product WHERE product_id = :productId";
        Map<String, Object>map = new HashMap<>();
        map.put("productId", id);
        namedParameterJdbcTemplate.update(sql, map);
    }
}
