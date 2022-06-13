package cn.paopao.zuchebackend.controller;

import cn.paopao.zuchebackend.bean.PageBean;
import cn.paopao.zuchebackend.entity.Car;
import cn.paopao.zuchebackend.entity.Category;
import cn.paopao.zuchebackend.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/car")
public class CarController {

    @Autowired
    CarService carService;

    @GetMapping("/list")
    public String index(Model model, Car car, PageBean<Car> pageBean){
        model.addAttribute("title", "汽车信息列表");
        model.addAttribute("pageBean",carService.queryAllByLimit(car,pageBean));
        return "/admin/car/list";
    }
}
