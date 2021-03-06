package com.saihou.jishop.service.impl;

import com.saihou.jishop.entity.Order;
import com.saihou.jishop.entity.OrderItem;
import com.saihou.jishop.entity.Product;
import com.saihou.jishop.entity.User;
import com.saihou.jishop.mapper.OrderItemMapper;
import com.saihou.jishop.service.OrderItemService;
import com.saihou.jishop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * オーダー詳細
 *
 * @author saihou
 * @date 2021/04/20
 */
@SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
@Service("orderItemService")
public class OrderItemServiceImpl implements OrderItemService {

    @Autowired
    @Qualifier("orderItemMapper")
    private OrderItemMapper orderItemMapper;
    @Autowired
    private ProductService productService;

    @Override
    public List<OrderItem> findAll() {
        return orderItemMapper.findAll();
    }

    @Override
    public OrderItem findById(Integer id) {
        return orderItemMapper.findById(id);
    }

    @Override
    public List<OrderItem> findByOid(Integer oid) {
        return orderItemMapper.findByOid(oid);
    }

    @Override
    public List<OrderItem> findByUid(Integer uid) {
        return orderItemMapper.findByUid(uid);
    }

    @Override
    public List<OrderItem> findByPid(Integer pid) {
        return orderItemMapper.findByPid(pid);
    }

    @Override
    public int insert(OrderItem orderItem) {
        return orderItemMapper.insert(orderItem);
    }

    @Override
    public int update(OrderItem orderItem) {
        return orderItemMapper.update(orderItem);
    }

    @Override
    public int delete(Integer id) {
        return orderItemMapper.delete(id);
    }

    @Override
    public void calculateAll(List<Order> orders) {
        for (Order order : orders) {
            calculate(order);
        }
    }

    @Override
    public void calculate(Order order) {
        List<OrderItem> items = orderItemMapper.findByOid(order.getId());

        float amount = 0f;
        int number = 0;

        for (OrderItem item : items) {
            amount += item.getNumber() * item.getProduct().getPromotePrice();
            number += item.getNumber();
        }

        order.setAmount(amount);
        order.setNumber(number);
    }

    @Override
    public int checkOrderItem(User user, Integer pid, Integer num) {
        Product product = productService.findById(pid);
        List<OrderItem> orderItems = findByUid(user.getId());

        int orderItemId = 0;
        boolean isExist = false;

        for (OrderItem orderItem : orderItems) {
            if (orderItem.getProduct().getId().equals(product.getId())) {
                orderItem.setNumber(orderItem.getNumber() + num);
                update(orderItem);

                isExist = true;
                orderItemId = orderItem.getId();
                break;
            }
        }

        if (!isExist) {
            OrderItem orderItem = new OrderItem();
            orderItem.setUid(user.getId());
            orderItem.setPid(pid);
            orderItem.setNumber(num);

            insert(orderItem);
            orderItemId = orderItem.getId();
        }

        return orderItemId;
    }

    @Override
    public int getSaleCount(Integer pid) {
        List<OrderItem> orderItems = findByPid(pid);
        int saleCount = 0;

        for (OrderItem orderItem : orderItems) {
            saleCount += orderItem.getNumber();
        }

        return saleCount;
    }
}
