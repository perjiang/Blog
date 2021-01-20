package com.jx.blog.Dao;


import com.jx.blog.entity.Admin;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface AdminDao {
    Admin selectAll(String username);

    Admin selectAdminByName(String username);

    void add(Admin admin);

    void addRole(@Param("adminId") int adminId,@Param("roleId")  int roleId);

    List<Admin> showAllUser();

    int getUserNum(String username);

    void deleteById(int id);

    void deleteAdminRole(int id);

    Admin selectById(int id);

    void update(Admin admin);
}
