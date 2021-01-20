package com.jx.blog.config;


import com.jx.blog.Dao.AdminDao;
import com.jx.blog.entity.Admin;
import com.jx.blog.entity.Role;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

public class ShiroRealm extends AuthorizingRealm {


    @Autowired
    AdminDao adminDao;

    /**
     *
     * @param principals  doGetAuthenticationInfo返回对象的第一个参数
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String username = principals.getPrimaryPrincipal().toString();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        Admin admin = adminDao.selectAll(username);
        Set<String> set = new HashSet<>();
        for (Role role : admin.getRoles()) {
            set.add(role.getRolename());
        }
        info.setRoles(set);
        return info;
    }

    /**
     *
     * @param token  suject.login(token)
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken tokenstr = (UsernamePasswordToken)token;
        String username = tokenstr.getUsername();
        Admin admin = adminDao.selectAdminByName(username);
        if (admin==null){
            throw new AuthenticationException();
        }
        String password = admin.getPassword();
        String salt = admin.getSalt();
        return new SimpleAuthenticationInfo(username,password, ByteSource.Util.bytes(salt),getName());
    }
}
