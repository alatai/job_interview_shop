package com.saihou.jishop.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.saihou.jishop.entity.Order;
import com.saihou.jishop.service.OrderItemService;
import com.saihou.jishop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * オーダー
 *
 * @author saihou
 * @date 2021/04/20
 */
@SuppressWarnings({"SpringJavaAutowiredFieldsWarningInspection"})
@Controller("orderController")
@RequestMapping("/admin/order")
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderItemService orderItemService;

    @RequestMapping(value = {"/index", "/list"})
    public String list(Model model, Integer currentPage) {
        currentPage = currentPage == null ? 1 : currentPage;
        PageHelper.startPage(currentPage, 5);
        List<Order> orders = orderService.findAll();
        orderItemService.calculateAll(orders);
        PageInfo<Order> pageInfo = new PageInfo<>(orders);

        model.addAttribute("title", "オーダー管理");
        model.addAttribute("orders", orders);
        model.addAttribute("pageInfo", pageInfo);

        return "/admin/order/list";
    }

    /**
     * オーダー配達完成、状態変更（waitConfirm）
     */
    @RequestMapping("/deliver")
    public String deliver(Integer id) {
        orderService.deliver(id);

        return "redirect:/admin/order/list";
    }
}
