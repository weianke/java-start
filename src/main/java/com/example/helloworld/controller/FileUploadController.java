package com.example.helloworld.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

@RestController
public class FileUploadController {

    @PostMapping("/upload")
    public String up(String nickName, MultipartFile photo, HttpServletRequest request) throws IOException {
        System.out.println(nickName);
        // 获取图片的原始名称
        System.out.println(photo.getOriginalFilename());
        // 获取文件类型
        System.out.println(photo.getContentType());

        String path = request.getServletContext().getRealPath("/upload/");
        System.out.println(path);
        saveFile(photo, path);

        return "上传成功";
    }

    // 内部工作函数，不需要返回使用void
    public void saveFile(MultipartFile photo, String path) throws IOException {
        // 1. 把传入的路径包装成 File 对象，方便判断目录是否存在
        File dir = new File(path);
        // 2. 如果目录不存在
        if(!dir.exists()) {
            // 创建目录
            boolean success = dir.mkdir();
            if (!success) {
                throw new IOException("创建目录失败：" + path);
            }
        }
        // 4. 拼接完整文件路径：目录 + 原始文件名
        File file = new File(path+photo.getOriginalFilename());
        // 5. 把上传的文件写入到目标文件
        photo.transferTo(file);
    }

}
