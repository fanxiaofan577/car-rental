package cn.paopao.zuchebackend.controller;

import cn.paopao.zuchebackend.bean.PageBean;
import cn.paopao.zuchebackend.entity.RoughlyCar;
import cn.paopao.zuchebackend.service.RoughlyCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/roughlyCars")
public class RoughlyCarController {

    @Autowired
    RoughlyCarService roughlyCarService;

    @GetMapping("/list")
    public String index(Model model, RoughlyCar roughlyCar, PageBean<RoughlyCar> pageBean){
        model.addAttribute("title", "分类信息列表");
        PageBean gb = roughlyCarService.queryAllByLimit(roughlyCar, pageBean);
        System.out.println(gb);
        model.addAttribute("pageBean",gb);
        return "/admin/RoughlyCar/list";
    }
}
