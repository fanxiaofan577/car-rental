package cn.paopao.zuchebackend.mapper;

import cn.paopao.zuchebackend.entity.Account;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AccountMapper {

    @Select("SELECT * FROM account WHERE username= #{username}")
    Account loadAccountByUsername(String username);

    long count(Account account);

    List<Account> queryAllByLimit(Account account);
}
