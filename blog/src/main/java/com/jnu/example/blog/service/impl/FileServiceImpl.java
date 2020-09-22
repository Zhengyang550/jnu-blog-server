package com.jnu.example.blog.service.impl;
import com.jnu.example.blog.service.AbstractFileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 文件服务
 * Created by zy on 2020/2/27.
 */
@Service
@Slf4j
public class FileServiceImpl extends AbstractFileService {

    /**
     * @author: zy
     * @description: 用于自定义上传文件保存到服务器的名称
     * @date: 2020/2/29 12:46
     * @param dir: 上传目录
     * @param originalFilename: 原始上传的文件名
     * @return String:
     */
    @Override
    protected String getFileName(String dir, String originalFilename) {
        int lastPot = originalFilename.lastIndexOf('.');
        String suffix = "";
        if (lastPot > -1) {
            suffix = originalFilename.substring(lastPot);
        }
        return System.nanoTime() + suffix;
    }
}
