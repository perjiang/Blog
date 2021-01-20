package com.jx.blog.Service.Impl;

import com.jx.blog.Dao.BlogDao;
import com.jx.blog.Service.BlogService;
import com.jx.blog.entity.Blog;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {
    @Autowired
    private BlogDao blogDao;

    @Override
    public List<Blog> Recently_BlogsByTime() {
        SimpleDateFormat format = new SimpleDateFormat();
        List<Blog> blogs = blogDao.Recently_BlogsByTime();
//        for (Blog blog : blogs) {
//            format.
//        }
        return blogs;
    }
}
