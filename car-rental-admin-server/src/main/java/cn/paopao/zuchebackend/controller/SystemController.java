package cn.paopao.zuchebackend.controller;

import cn.paopao.zuchebackend.entity.Account;
import cn.paopao.zuchebackend.entity.Car;
import cn.paopao.zuchebackend.entity.Order;
import cn.paopao.zuchebackend.service.AccountService;
import cn.paopao.zuchebackend.service.CarService;
import cn.paopao.zuchebackend.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;

@Controller
public class SystemController {

    @Autowired
    OrderService orderService;
    @Autowired
    CarService carService;
    @Autowired
    AccountService accountService;

    /**
     * 登录页面
     * @param model
     * @return
     */
    @GetMapping("/login")
    public String login(Model model){
        return "/admin/system/login";
    }

    /**
     * 登录成功后的系统主页
     * @param model
     * @return
     */
    @RequestMapping(value="/index")
    public String index(Model model){
        model.addAttribute("userTotal", accountService.total(new Account()));
        model.addAttribute("orderTotal", orderService.total(new Order()));
        model.addAttribute("carTotal", carService.total(new Car()));
        return "admin/system/index";
    }
}
