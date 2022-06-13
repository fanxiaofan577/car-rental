package cn.paopao.zuchebackend.config;

import cn.paopao.zuchebackend.entity.Permission;
import cn.paopao.zuchebackend.entity.Role;
import cn.paopao.zuchebackend.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.List;

@Component
public class CustomFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    @Autowired
    PermissionService permissionService;

    AntPathMatcher antPathMatcher= new AntPathMatcher();

    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        String requestUrl = ((FilterInvocation) o).getRequestUrl();
        List<Permission> permissions = permissionService.getPermissions();
        for (Permission a : permissions) {
            if (antPathMatcher.match(a.getUrl(),requestUrl)){
                List<Role> roles = a.getRoles();
                String[] strs = new String[roles.size()];
                for (int i = 0; i < roles.size(); i++) {
                    strs[i] = roles.get(i).getName();
                }
                return SecurityConfig.createList(strs);
            }
        }
        return SecurityConfig.createList("ROLE_LOGIN");
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
