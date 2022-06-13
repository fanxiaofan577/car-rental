package cn.paopao.zuchebackend.controller;

import cn.paopao.zuchebackend.bean.PageBean;
import cn.paopao.zuchebackend.entity.Account;
import cn.paopao.zuchebackend.entity.Category;
import cn.paopao.zuchebackend.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @GetMapping("/list")
    public String index(Model model, Category category, PageBean<Category> pageBean){
        model.addAttribute("title", "分类信息列表");
        model.addAttribute("pageBean",categoryService.queryAllByLimit(category,pageBean));
        return "/admin/category/list";
    }
}
