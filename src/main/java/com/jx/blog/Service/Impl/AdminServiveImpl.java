package com.jx.blog.Service.Impl;

import com.jx.blog.Dao.AdminDao;
import com.jx.blog.Service.AdminService;
import com.jx.blog.Service.RoleService;
import com.jx.blog.entity.Admin;
import com.jx.blog.entity.Role;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.codec.json.KotlinSerializationJsonEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class AdminServiveImpl implements AdminService {
    @Autowired
    private AdminDao adminDao;
    @Autowired
    private RoleService roleService;
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;


    @Override
    public Admin login(Admin admin) {
        String username = admin.getUsername();
        String password = admin.getPassword();
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
        Subject subject = SecurityUtils.getSubject();
        subject.login(token);
        return admin;
    }

    @Override
    public void add(Admin admin) {
        adminDao.add(admin);
        Integer adminId = admin.getId();
        Role role = roleService.selectOhterRole();
        int roleId = role.getId();
        redisTemplate.opsForValue().set(String.valueOf(adminId),admin.getSalt());
        adminDao.addRole(adminId,roleId);
    }

    @Override
    public List<Admin> showAllUser() {
        return adminDao.showAllUser();
    }

    @Override
    public int getUserNum(String username) {
        int count = adminDao.getUserNum(username);
        return count;
    }

    @Override
    public void deleteAdmin(int id) {
        adminDao.deleteById(id);
        adminDao.deleteAdminRole(id);
    }

    @Override
    public Admin selectAdmin(int id) {

        return  adminDao.selectById(id);
    }

    @Override
    public void update(Admin admin) {
        adminDao.update(admin);
    }
}
