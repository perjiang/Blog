package com.jx.blog.Controller;

import com.jx.blog.Service.BlogService;
import com.jx.blog.entity.Blog;
import com.jx.blog.entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BlogController {
    @Autowired
    private BlogService blogService;

    @GetMapping("/Recently_Blogs")
    public Result Recently_Blogs(){
        try {
            List<Blog> blogs = blogService.Recently_BlogsByTime();
            return new Result(true,blogs);
        }catch (Exception e){
            return new Result(false,"查询出错");
        }
    }
}
