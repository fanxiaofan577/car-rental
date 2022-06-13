package cn.paopao.zuchebackend.controller;

import cn.paopao.zuchebackend.bean.PageBean;
import cn.paopao.zuchebackend.entity.Order;
import cn.paopao.zuchebackend.entity.SystemResults;
import cn.paopao.zuchebackend.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
    @RequestMapping("/admin/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @RequestMapping("/list")
    public String findAllList(Model model, Order order, PageBean<Order> pageBean) {
        model.addAttribute("title", "订单信息列表");
        model.addAttribute("pageBean",orderService.queryAllByLimit(order,pageBean));
        return "/admin/order/admin_list";
    }

    @RequestMapping("/backCar")
    @ResponseBody
    public SystemResults backCar(Order order) {
        return orderService.backCar(order);
    }
}
