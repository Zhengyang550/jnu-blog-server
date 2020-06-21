package com.jnu.example.core.util;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.crypto.Mode;
import cn.hutool.crypto.Padding;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.digest.MD5;
import cn.hutool.crypto.symmetric.AES;
import com.jnu.example.core.util.JnuCoderUtil;
import lombok.extern.slf4j.Slf4j;


/**
 * Author: zy
 * Description: 采用AES对称加密算法 对系统用户密码进行加密解密
 * https://github.com/wustrive2008/aes-rsa-java
 * https://github.com/chenerzhu/common-secure
 * Date: 2020/3/20
 */
@Slf4j
public class JnuEncryptUtil {
    /**
     * 用于生成秘钥
     */
    private static final String SECRET = "zjnu";

    /**
     * @author: zy
     * @description: 构造方法
     * @date: 2020/3/20 13:37
     * @return :
     */
    private JnuEncryptUtil(){}

    /**
     * @author: zy
     * @description: 创建AES密码器
     * @date: 2020/3/20 13:09
     * @return AES: AES密码器
     */
    private static AES getInstance(){
        //使用MD5处理生成长度为16的字节数组 作为秘钥
        MD5 md5 = SecureUtil.md5();
        md5.setDigestCount(16);
        byte[] secretKey = md5.digest(SECRET);

        //创建加密对象  使用CBC模式，需要一个向量iv，可增加加密算法的强度
        return new AES(Mode.CBC, Padding.PKCS5Padding,secretKey,secretKey);
    }

    /**
     * @author: zy
     * @description: 对用户原始密码进行加密
     * @date: 2020/3/20 12:23
     * @param password: 登录密码
     * @return String: 密文
     */
   public static String encryptToBase64(String password){
       //对AES算法加密后的字节数组，采用Base64编码
       return getInstance().encryptBase64(password, CharsetUtil.CHARSET_UTF_8);
   }

    /**
     * @author: zy
     * @description: 解密  如果解密失败返回""
     * @date: 2020/3/20 12:23
     * @param password: 加密后的密文
     * @return String: 原始密码
     */
    public static String decryptFromBase64(String password){
        try {
            //对密文限采用Base64解码
            return getInstance().decryptStr(JnuCoderUtil.decodeBase64(password),  CharsetUtil.CHARSET_UTF_8);
        } catch (Exception e) {
            log.error(String.format("登录密码(%s)解密失败:",password),e);
            return "";
        }
    }
}
