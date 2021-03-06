package com.saihou.jishop.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.saihou.jishop.entity.User;
import com.saihou.jishop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * ユーザ
 *
 * @author saihou
 * @date 2021/04/20
 */
@SuppressWarnings({"SpringJavaAutowiredFieldsWarningInspection"})
@Controller("userController")
@RequestMapping("/admin/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = {"/index", "/list"})
    public String list(Model model, Integer currentPage) {
        currentPage = currentPage == null ? 1 : currentPage;
        PageHelper.startPage(currentPage, 5);
        List<User> users = userService.findAll();
        PageInfo<User> pageInfo = new PageInfo<>(users);

        model.addAttribute("title", "ユーザ管理");
        model.addAttribute("users", users);
        model.addAttribute("pageInfo", pageInfo);

        return "/admin/user/list";
    }
}
