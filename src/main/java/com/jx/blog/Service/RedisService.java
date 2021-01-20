package com.jx.blog.Service;

import com.jx.blog.entity.Tags;

import java.util.List;

public interface RedisService {
    List<Tags> selectAllTags();
}
