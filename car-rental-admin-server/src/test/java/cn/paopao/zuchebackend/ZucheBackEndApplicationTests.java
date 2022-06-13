package cn.paopao.zuchebackend;

import cn.paopao.zuchebackend.entity.Account;
import cn.paopao.zuchebackend.mapper.AccountMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class ZucheBackEndApplicationTests {
    @Autowired
    AccountMapper accountMapper;

    @Test
    void contextLoads() {
        Account account = new Account();
        account.setNickName("七五七");
        List<Account> accounts = accountMapper.queryAllByLimit(account);
        System.out.println(accounts);
    }
}
