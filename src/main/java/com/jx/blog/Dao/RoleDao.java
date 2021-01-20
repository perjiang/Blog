package com.jx.blog.Dao;

import com.jx.blog.entity.Role;

import java.util.List;

public interface RoleDao {
    List<Role> selectRoleByAdminId(int id);

    Role selectOhterRole();
}
