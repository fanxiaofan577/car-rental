package cn.paopao.zuchebackend.mapper;

import cn.paopao.zuchebackend.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RoleMapper {

    @Select("select r.* from role r ,account_role ar where ar.role_id =r.role_id and ar.account_id = #{id}")
    List<Role> getRolesAccountByid(Integer id);

}
