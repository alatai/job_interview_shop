package com.saihou.jishop.controller.shop;

import com.saihou.jishop.entity.Category;
import com.saihou.jishop.service.CategoryService;
import com.saihou.jishop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author saihou
 * @date 2021/04/21
 */
@SuppressWarnings({"SpringJavaAutowiredFieldsWarningInspection"})
@Controller("indexController")
@RequestMapping("/")
public class IndexController {

    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;

    @RequestMapping("/index")
    public String index(Model model) {
        List<Category> categories = categoryService.findAll();
        productService.fillRows(categories);

        model.addAttribute("title", "jishop");
        model.addAttribute("categories", categories);

        return "shop/home/index";
    }

}
