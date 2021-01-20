package com.jx.blog.Dao;

import com.jx.blog.entity.Blog;

import java.util.List;

public interface BlogDao {
    List<Blog> Recently_BlogsByTime();
}
