package com.jx.blog.entity;

import lombok.Data;

@Data
public class FileInfo {
    private int flag;
    private String message;
    private String FilePath;





    public FileInfo(){}

    public FileInfo(int flag, String message, String filePath) {
        this.flag = flag;
        this.message = message;
        FilePath = filePath;
    }
}
