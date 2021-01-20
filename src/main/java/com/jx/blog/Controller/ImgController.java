package com.jx.blog.Controller;

import com.jx.blog.config.OSSProperties;
import com.jx.blog.entity.FileInfo;
import com.jx.blog.utils.ResultEntity;
import com.jx.blog.utils.UploadUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/ImgFile")
@Slf4j
public class ImgController {

//    @Value("${img.location}")
//    private String folder;

    @Autowired
    private OSSProperties ossProperties;

    @PostMapping
    public Map<String,Object> upload(HttpServletRequest request, @RequestParam(value = "editormd-image-file", required = false) MultipartFile file){
        Map<String,Object> resultMap = new HashMap<>();
        log.info("【FileController】 fileName={},fileOrginNmae={},fileSize={}", file.getName(), file.getOriginalFilename(), file.getSize());
        log.info(request.getContextPath());
        String filename = file.getOriginalFilename();
        String suffix = filename.substring(filename.lastIndexOf(".")+1 );
        String newFileName = new Date().getTime() + "." + suffix;
//        File localFile = new File(folder,newFileName);
        try {
            ResultEntity<String> uploadHeaderPicResultEntity = UploadUtil.uploadFileToOss(
                    ossProperties.getEndPoint(),
                    ossProperties.getAccessKeyId(),
                    ossProperties.getAccessKeySecret(),
                    file.getInputStream(),
                    ossProperties.getBucketName(),
                    ossProperties.getBucketDomain(),
                    file.getOriginalFilename()
            );
            String url = uploadHeaderPicResultEntity.getData();
            resultMap.put("success", 1);
            resultMap.put("message", "上传成功!");
            resultMap.put("url",url);
            log.info(url);
//            log.info(localFile.getAbsolutePath());
        }catch (Exception e){
            resultMap.put("success", 0);
            resultMap.put("message", "上传失败！");
            e.printStackTrace();
        }
        return resultMap;
    }

//    @GetMapping("/{id}")
//    public void downLoad(@PathVariable String id, HttpServletRequest request, HttpServletResponse response) {
//        try (InputStream inputStream = new FileInputStream(new File(folder, id + ".txt"));
//             OutputStream outputStream = response.getOutputStream();) {
//            response.setContentType("application/x-download");
//            response.setHeader("Content-Disposition", "attachment;filename=test.txt");
//
//            IOUtils.copy(inputStream, outputStream);
//            outputStream.flush();
//        } catch (Exception e) {
//
//        }
//    }

}
