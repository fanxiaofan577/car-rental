package cn.paopao.zuchebackend.service;

import cn.paopao.zuchebackend.bean.PageBean;
import cn.paopao.zuchebackend.entity.Account;
import cn.paopao.zuchebackend.entity.Role;
import cn.paopao.zuchebackend.mapper.AccountMapper;
import cn.paopao.zuchebackend.mapper.RoleMapper;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService implements UserDetailsService {

    @Autowired
    AccountMapper accountMapper;
    @Autowired
    RoleMapper roleMapper;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Account account = accountMapper.loadAccountByUsername(s);
        if (account == null){
            throw new UsernameNotFoundException("用户不存在！！");
        }
        List<Role> roles = roleMapper.getRolesAccountByid(account.getAccountId());
        account.setRoles(roles);
        return account;
    }

    /**
     * 返回用户总数
     * @return
     */
    public long total(Account account){
        return accountMapper.count(account);
    }

    public PageBean queryAllByLimit(Account account, PageBean pageBean){
        PageHelper.startPage(pageBean.getPageNum(), pageBean.getPageSize());
        List<Account> accounts = accountMapper.queryAllByLimit(account);
        long count = accountMapper.count(account);
        pageBean.setTotal(count);
        pageBean.setContent(accounts);
        pageBean.setTotalPage(pageBean.getTotal()/ pageBean.getPageSize()+1);
        return pageBean;
    }
}
