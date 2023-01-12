package com.min.cardshop.rowMapper;

import com.min.cardshop.constant.ProductCategory;
import com.min.cardshop.model.Product;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductRowMapper implements RowMapper<Product> {
    @Override
    public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
        Product product = new Product();
        product.setProductID(rs.getInt("product_id"));
        product.setProductName(rs.getString("product_name"));

        //從資料庫撈過來是String
        String strCategory = rs.getString("category");

        //轉成Enum型別
        ProductCategory category = ProductCategory.valueOf(strCategory);
        product.setCategory(category);

        product.setImageUrl(rs.getString("image_url"));
        product.setPrice(rs.getInt("price"));
        product.setStock(rs.getInt("stock"));
        product.setDescription(rs.getString("description"));
        product.setCreatedDate(rs.getDate("created_date"));
        product.setLastModifiedDate(rs.getTimestamp("last_modified_date"));

        return product;
    }
}
