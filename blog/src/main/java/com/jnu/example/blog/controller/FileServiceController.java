package com.jnu.example.blog.controller;

import com.jnu.example.blog.service.AbstractFileService;
import com.jnu.example.core.pojo.CustomizedResponseEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;

/**
 * @Author： zy
 * @Date：2020/9/22
 * @Description：
 */

@Api(tags = "上传下载文件接口")
@RestController
@RequestMapping("/blog/api/attachment")
public class FileServiceController {
    @Autowired
    @Qualifier("fileServiceImpl")
    AbstractFileService fileService;

    @ApiOperation(value = "文件上传")
    @PostMapping("/upload")
    public CustomizedResponseEntity<String> upload(@ApiParam(value = "上传文件",required = true) @NotNull(message = "文件不能为空")
                                                       @RequestPart("file") MultipartFile file) {
        return CustomizedResponseEntity.success(fileService.uploadFile(file));
    }

    @ApiOperation(value = "文件下载")
    @GetMapping("/download/{filename}")
    public void download(@ApiParam(value = "下载文件",required = true) @NotNull(message = "文件名不能为空") @PathVariable("filename") String fileName,
                         HttpServletResponse response) {
        fileService.downloadFile(response,fileName);
    }
}
