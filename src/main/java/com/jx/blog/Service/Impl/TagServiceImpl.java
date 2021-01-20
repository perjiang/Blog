package com.jx.blog.Service.Impl;

import com.jx.blog.Dao.TagDao;
import com.jx.blog.Service.TagService;
import com.jx.blog.entity.Tags;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    TagDao tagDao;

    public List<Tags> selectAllTags(){
        List<Tags> tags = tagDao.selectAllTags();
        return tags;
    }
}
