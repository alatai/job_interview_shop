package com.saihou.jishop.mapper;

import com.saihou.jishop.entity.Property;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 商品属性
 *
 * @author saihou
 * @date 2021/04/19
 */
@Repository("propertyMapper")
public interface PropertyMapper {

    List<Property> findAll();

    Property findById(Integer id);

    List<Property> findByCid(Integer cid);

    int insert(Property record);

    int update(Property record);

    int delete(Integer id);
}