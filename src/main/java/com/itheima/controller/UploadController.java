package com.itheima.controller;

import com.itheima.pojo.Result;
import com.itheima.utils.AliOSSUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@RestController

public class UploadController {
    /*
    // 本地存储方法
    @PostMapping("/upload")
    public Result upload(String username, Integer age, MultipartFile image) throws Exception {
        log.info("文件上传：{},{},{}",username,age,image);
        // 获取原始文件名
        String originalFilename = image.getOriginalFilename();

        // 构造唯一的文件名（不能重复）---- uuid(通用唯一识别码)
        // 构建新的文件名
        String extname = originalFilename.substring(originalFilename.lastIndexOf("."));//文件扩展名(.jpg)
        String newFileName = UUID.randomUUID().toString() + extname;//随机名+文件扩展名
        log.info("新的文件名：{}", newFileName);

        // 将文件存储在本地磁盘目录
        image.transferTo(new File("/Users/ppx/Pictures/project_images/" + newFileName));
        return Result.success();

    }
    */
    @Autowired
    private AliOSSUtils aliOSSUtils;
    @PostMapping("/upload")
    public Result upload(MultipartFile image) throws IOException {
        log.info("文件上传：{}",image.getOriginalFilename());

        //调用阿里云OSS工具类，将上传上来的文件存入阿里云
        String url = aliOSSUtils.upload(image);
        log.info("文件上传完成后的url：{}",url);

        //将图片上传完成后的url返回，用于浏览器回显展示
        return Result.success(url);
    }

}