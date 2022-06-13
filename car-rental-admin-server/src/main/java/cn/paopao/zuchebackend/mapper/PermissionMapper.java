package cn.paopao.zuchebackend.mapper;

import cn.paopao.zuchebackend.entity.Permission;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PermissionMapper {

    List<Permission> findAllByAccountId(Long accountId);

    List<Permission> getPermissions();

}
