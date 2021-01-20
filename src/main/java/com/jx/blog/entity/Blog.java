package com.jx.blog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Blog {
    private int id;
    private String title;
    private String content;
//    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String createTime;
}
