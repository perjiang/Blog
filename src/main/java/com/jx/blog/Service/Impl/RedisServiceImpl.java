package com.jx.blog.Service.Impl;

import com.jx.blog.Service.RedisService;
import com.jx.blog.entity.Tags;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class RedisServiceImpl implements RedisService {

    @Autowired
    @Qualifier(value = "myRedisTemplate")
    private RedisTemplate<String,Object> redisTemplate;


    @Override
    public List<Tags> selectAllTags() {
        List<Tags> tags = Collections.singletonList((Tags) redisTemplate.opsForList().range("tags", 0, -1));
        return tags;
    }
}
