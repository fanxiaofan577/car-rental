package cn.paopao.zuchebackend.controller;

import cn.paopao.zuchebackend.bean.PageBean;
import cn.paopao.zuchebackend.entity.Account;
import cn.paopao.zuchebackend.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/account")
public class AccountConroller {

    @Autowired
    AccountService accountService;

    @GetMapping("/list")
    public String index(Model model, Account account, PageBean<Account> pageBean){
        account.setPassword(null);
        account.setOpenid(null);
        model.addAttribute("title", "用户信息列表");
        PageBean bean = accountService.queryAllByLimit(account, pageBean);
        model.addAttribute("pageBean",bean);
        return "/admin/user/list";
    }
}
