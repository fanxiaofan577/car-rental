package cn.paopao.zuchebackend.service;

import cn.paopao.zuchebackend.entity.Role;
import cn.paopao.zuchebackend.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
    @Autowired
    RoleMapper roleMapper;

    public List<Role> getRolesAccountByid(Integer id){
        return roleMapper.getRolesAccountByid(id);
    }
}
