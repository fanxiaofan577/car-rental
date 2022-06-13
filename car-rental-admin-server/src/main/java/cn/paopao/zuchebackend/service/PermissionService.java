package cn.paopao.zuchebackend.service;

import cn.paopao.zuchebackend.entity.Permission;
import cn.paopao.zuchebackend.mapper.PermissionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@CacheConfig(cacheNames = "permission_cache")
public class PermissionService {

    @Autowired
    PermissionMapper permissionMapper;
    @Cacheable
    public List<Permission> getPermissions(){
        return permissionMapper.getPermissions();
    }
}
