package com.jnu.example.blog.service;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.URLUtil;
import com.jnu.example.core.constant.ContentType;
import com.jnu.example.core.constant.enums.ResponseCode;
import com.jnu.example.core.exception.BusinessException;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

/**
 * @Author： zy
 * @Date：2020/9/22
 * @Description：文件上传和下载抽象类
 */

@Data
@Slf4j
public abstract class AbstractFileService {
    /**
     * @author: zy
     * @description: 抽象方法 获取上传或者下载的文件的基路径  可以重写该方法，改变文件存放的基路径
     * @date: 2020/5/7 11:29
     * @return ：返回文件基路径
     */
    protected String getPathPrefix(String rootPath) {
        if(StrUtil.isBlank(rootPath)){
            rootPath = "./uploads";
        }
        //处理 ./开头的根路径
        if (rootPath.startsWith("./")) {
            rootPath = System.getProperty("user.dir") + File.separator + rootPath.replace("./","");
        }
        File file = new File(rootPath);
        if(!file.exists()){
            file.mkdirs();
        }
        return rootPath;
    }

    /**
     * @author: zy
     * @description: 用于自定义上传文件保存到服务器的名称
     * @date: 2020/5/7 11:29
     * @param pathPrefix：文件所在路径
     * @param originalFilename：原始文件名
     * @return ：返回保存文件名
     */
    protected String getFileName(String pathPrefix,String originalFilename) {
        // 检测文件名重复
        File f = new File( pathPrefix + File.separator + originalFilename);
        if (f.exists()) {
            int splitIndex = originalFilename.lastIndexOf('.');
            StringBuilder sb = new StringBuilder(originalFilename);
            sb.insert(splitIndex,"_" + System.currentTimeMillis());
            return sb.toString();
        } else {
            return originalFilename;
        }
    }

    /**
     * @author: zy
     * @description: 删除基路径下的文件
     * @date: 2020/5/7 11:29
     * @param fileName：待删除的文件名
     * @return Boolean：
     */
    public Boolean deleteFile(String fileName){
        //获取保存文件的基路径
        String pathPrefix = getPathPrefix(null);

        //删除文件
        if(!FileUtil.del(pathPrefix + File.separator + fileName)){
            log.error(String.format("file %s delete fail",fileName));
            return false;
        }
        return true;
    }

    /**
     * @author: zy
     * @description: 删除基路径下的文件列表
     * @date: 2020/5/7 11:29
     * @param fileNames：待删除的文件列表
     * @return ：
     */
    public void deleteFile(List<String> fileNames){
        for(String fileName:fileNames){
            deleteFile(fileName);
        }
    }

    /**
     * @author: zy
     * @description: 上传文件
     * @date: 2020/5/7 11:29
     * @param file:上传文件
     * @return String:
     */
    public String uploadFile(MultipartFile file) {
        //获取保存文件的基路径
        String pathPrefix = getPathPrefix("");

        //如果文件名称已经存在 获取新的文件名
        String fileName = getFileName(pathPrefix,file.getOriginalFilename());

        //创建文件对象 保存文件
        File newFile = new File(pathPrefix + File.separator + fileName);
        try {
            file.transferTo(newFile);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            throw new BusinessException(ResponseCode.FILE_UPLOAD_FAIL);
        }
        return fileName;
    }

    /**
     * @author: zy
     * @description: 从基路径中下载文件
     * @date: 2020/5/7 11:29
     * @param response:
     * @param fileName: 文件名
     * @return void:
     */
    public void downloadFile(HttpServletResponse response, String fileName){
        //获取保存文件的基路径
        String pathPrefix = getPathPrefix("");

        //创建文件对象 下载文件
        File f = new File(pathPrefix + File.separator + fileName);
        if (!f.exists()) {
            log.error(String.format("request file %s does not exist",fileName));
            response.setStatus(HttpStatus.NOT_FOUND.value());
            return;
        }

        try (BufferedInputStream br = new BufferedInputStream(new FileInputStream(f));
             OutputStream out = response.getOutputStream()) {
            byte[] buf = new byte[1024];
            int len = 0;
            response.reset();
            response.setContentType(ContentType.getContentType(fileName));
            response.setHeader("Content-Disposition", "attachment; filename=" + URLUtil.encode(fileName));
            while ((len = br.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            out.flush();
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }
}
