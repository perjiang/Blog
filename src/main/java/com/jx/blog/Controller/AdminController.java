package com.jx.blog.Controller;

import com.alibaba.druid.sql.dialect.mysql.ast.MysqlForeignKey;
import com.jx.blog.Service.AdminService;
import com.jx.blog.entity.Admin;
import com.jx.blog.entity.Result;
import com.jx.blog.entity.vo.RegisterVO;
import com.jx.blog.utils.Md5Util;
import lombok.AllArgsConstructor;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.credential.PasswordService;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.crypto.PaddingScheme;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.function.SupplierUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.UUID;

@Controller
public class AdminController {
    @Autowired
    private AdminService adminService;
    @Autowired
    RedisTemplate<String,Object> redisTemplate;



    @PostMapping("/login")
    public String login(Admin admin, HttpSession session, Model model){
        try {
            System.out.println(admin);
            Admin login = adminService.login(admin);
            return "redirect:/admin.html";
        }catch (IncorrectCredentialsException e){
            model.addAttribute("msg", "账号密码错误");
            return "admin";
        }
    }

    @RequiresRoles(value = {"admin","other"})
    @RequestMapping("/admin.html")
    public String adminList(){
        return "adminIndex";
    }

    @ResponseBody
    @PostMapping("/register")
    public Result register(@RequestBody RegisterVO registerVO){
        if (registerVO.getInviteCode().equals("woshinibaba") == false){
            return new Result(false,"邀请码不正确");
        }
        String salt = UUID.randomUUID().toString();
        Admin admin = new Admin();
        Md5Hash md5Hash = new Md5Hash(registerVO.getPassword(), salt, 3);
        String newPassWord = md5Hash.toHex();
        admin.setSalt(salt);
        admin.setUsername(registerVO.getUsername());
        admin.setPassword(newPassWord);
        adminService.add(admin);
        return new Result(true,null);
    }

    @RequiresRoles(value = "admin")
    @GetMapping("/showAllUser")
    @ResponseBody
    public Result showAllUser(){
        try {
            List<Admin> adminList = adminService.showAllUser();
            return new Result(true,adminList);
        }
        catch (Exception e){
            return new Result(false,"查询出错");
        }

    }

    @RequiresRoles(value = "admin")
    @GetMapping("getUserNum")
    @ResponseBody
    public Result getUserNum(String username){
        int count = adminService.getUserNum(username);
        if (count>0){
            return new Result(false, "改账号已经被人注册了");
        }
        return new Result(true,null);
    }

//    @RequiresRoles(value = "admin")
//    @RequestMapping("/delete")
//    @ResponseBody
//    public String delete(){
//        return "delete";
//    }



    @RequiresRoles(value = "admin")
    @GetMapping("/admin/delete/{id}")
    public String adminDelete(@PathVariable("id") int id){

            adminService.deleteAdmin(id);
            return "redirect:/admin.html";
    }

    @RequiresRoles(value = "admin")
    @GetMapping("/admin/update/{id}")
    public String adminUpdate(@PathVariable("id") int id,Model model){
        Admin admin = adminService.selectAdmin(id);
        model.addAttribute("admin", admin);
        return "admin-update";
    }

    @RequiresRoles(value = "admin")
    @PostMapping("/admin/update")
    public String update(Admin admin){
        Integer id = admin.getId();
        String username = admin.getUsername();
        String password = admin.getPassword();
        String salt = (String)redisTemplate.opsForValue().get(String.valueOf(id));
        String newPassword = Md5Util.hash(password, salt, 3);
        admin.setPassword(newPassword);
        admin.setSalt(salt);
        adminService.update(admin);
        return "redirect:/admin.html";
    }

    @RequestMapping("/logout")
    public String logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "redirect:/";
    }

}
