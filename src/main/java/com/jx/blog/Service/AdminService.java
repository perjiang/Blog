package com.jx.blog.Service;

import com.jx.blog.entity.Admin;

import java.util.List;

public interface AdminService {
    Admin login(Admin admin);

    void add(Admin admin);

    List<Admin> showAllUser();

    int getUserNum(String username);

    void deleteAdmin(int id);

    Admin selectAdmin(int id);

    void update(Admin admin);
}
