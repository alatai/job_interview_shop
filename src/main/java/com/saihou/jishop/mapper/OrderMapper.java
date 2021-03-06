package com.saihou.jishop.mapper;

import com.saihou.jishop.entity.Order;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * オーダー
 *
 * @author saihou
 * @date 2021/04/20
 */
@Repository("orderMapper")
public interface OrderMapper {

    List<Order> findAll();

    Order findById(Integer id);

    List<Order> findByUid(Integer id);

    int insert(Order order);

    int update(Order order);

    int delete(Integer id);
}