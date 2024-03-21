package com.example.platform.controller;

import com.example.platform.pojo.Result;
import com.example.platform.utils.OSSUtil;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
@CrossOrigin
public class FileUploadController {
    @PostMapping("/upload")
    public Result<String> upload(MultipartFile file) throws Exception {
        //把文件内容存到本地磁盘上
        String originalFilename=file.getOriginalFilename();
        //为了防止图片被覆盖，前面加上一个uuid来确保文件名字是唯一的
        String filename= UUID.randomUUID().toString()+originalFilename.substring(originalFilename.lastIndexOf("."));
        //将文件上传到aliyunOSS，同时返回一个文件名
        String url = OSSUtil.FileSend(filename,file.getInputStream());
 /*       file.transferTo(new File("C:\\Users\\24654\\Pictures\\Saved Pictures\\"+filename));*/
        return Result.success(url);
    }

}
