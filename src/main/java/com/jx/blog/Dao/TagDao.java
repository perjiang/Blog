package com.jx.blog.Dao;

import com.jx.blog.entity.Tags;

import java.util.List;

public interface TagDao {

    List<Tags> selectAllTags();
}
