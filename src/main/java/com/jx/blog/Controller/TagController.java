package com.jx.blog.Controller;

import com.jx.blog.Service.RedisService;
import com.jx.blog.Service.TagService;
import com.jx.blog.entity.Result;
import com.jx.blog.entity.Tags;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.crypto.dsig.keyinfo.RetrievalMethod;
import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
public class TagController {

    @Autowired
    TagService tagService;

    @Autowired
    @Qualifier(value = "myRedisTemplate")
    private RedisTemplate<String,Object> redisTemplate;


    @GetMapping("/selectAllTags")
    public Result selectAllTags(){
        Result result = new Result();
        List<Tags> tags = new ArrayList<>();
        try {
            List<Object> redisTag = redisTemplate.opsForList().range("tags", 0, -1);
            if (redisTag != null && redisTag.size() > 0){
                log.info("redis");
                for (Object o : redisTag) {
                    Tags tag = (Tags)o;
                    tags.add(tag);
                }
                result.setData(tags);
            }
            if (tags.size() == 0){
                List<Tags> tags2 = tagService.selectAllTags();
                log.info("sql");
                result.setData(tags2);
                for (Tags tag : tags2) {
                    redisTemplate.opsForList().leftPush("tags", tag);
                }
            }
            result.setFlag(true);
        }catch (Exception e){
            result.setFlag(false);
            result.setData("查询出错");
        }
        return  result;
    }
}
