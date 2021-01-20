package com.jx.blog.Service.Impl;

import com.jx.blog.Dao.RoleDao;
import com.jx.blog.Service.RoleService;
import com.jx.blog.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDao roleDao;
    @Override
    public Role selectOhterRole() {
        Role role = roleDao.selectOhterRole();
        return role;
    }
}
