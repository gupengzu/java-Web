package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.pojo.Result;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
public class UploadController {

    //本地磁盘存储的方式
//    @PostMapping("/upload")
//    public Result upload(String name, Integer age, MultipartFile file) throws IOException {
//        log.info("上传文件,参数:{},{},{}",name,age,file);
//        String originalFilename = file.getOriginalFilename();
//        file.transferTo(new File("G:/photos/test/"+originalFilename));
//        return Result.success();
//    }


    @Autowired
    private AliyunOSSOperator aliyunOSSOperator;


    @PostMapping("/upload")
    public Result upload(MultipartFile file) throws Exception {
        log.info("上传文件,参数:{}",file.getOriginalFilename());
        //将文件交给OSS存储管理
        String url = aliyunOSSOperator.upload(file.getBytes(), file.getOriginalFilename());
        log.info("文件上传成功,url:{}",url);
        return Result.success(url);

    }
}
