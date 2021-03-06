package com.saihou.jishop.mapper;

import com.saihou.jishop.entity.Product;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 商品
 *
 * @author saihou
 * @date 2021/04/19
 */
@Repository("productMapper")
public interface ProductMapper {

    List<Product> findAll();

    Product findById(Integer id);

    List<Product> findByCid(Integer id);

    List<Product> findByKeyword(String keyword);

    int insert(Product product);

    int update(Product product);

    int delete(Integer id);

}