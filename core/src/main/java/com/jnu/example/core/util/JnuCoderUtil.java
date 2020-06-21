package com.jnu.example.core.util;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.ArrayUtil;

/**
 * Author: zy
 * Description: Base64编码解码、字节数组-十六进制编码解码
 * Date: 2020/3/20
 */
public class JnuCoderUtil {
    /**
     * @author: zy
     * @description: 构造方法
     * @date: 2020/3/20 13:37
     * @return :
     */
    private JnuCoderUtil(){}

    /**
     * @author: zy
     * @description: Base64 编码
     * @date: 2020/3/20 10:57
     * @param data:
     * @return java.lang.String:
     */
    public static String encodeBase64(byte[] data) {
        return Base64.encode(new String(data));
    }

    /**
     * @author: zy
     * @description: Base64 解码
     * @date: 2020/3/20 10:57
     * @param data:
     * @return byte[]:
     */
    public static byte[] decodeBase64(String data) {
        return Base64.decode(data.getBytes());
    }

    /**
     * @author: zy
     * @description: 将字节数组转换为十六进制字符串   如：['a','b','c','d'] => "61626364"
     * @date: 2020/3/20 10:45
     * @param data: 字节数组
     * @return String:
     */
    public static String encodeByteToHex(byte[] data) {
        if (ArrayUtil.isEmpty(data)) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        for (byte item:data) {
            int h = item & 0XFF;
            if (h < 16) {
                sb.append("0");
            }
            sb.append(Integer.toHexString(h));
        }

        return sb.toString();
    }

    /**
     * @author: zy
     * @description: 将十六进制字符串转换为字节数组
     * @date: 2020/3/20 10:46
     * @param hex: 十六进制字符串
     * @return byte[]:
     */
    public static byte[] decodeHex2Byte(String hex) {
        if (hex == null || "".equals(hex)) {
            return new byte[0];
        }

        int length = hex.length() >> 1;
        byte[] data = new byte[length];
        for (int i = 0; i < length; i++) {
            int n = i << 1;
            int height = Integer.valueOf(hex.substring(n, n + 1), 16);
            int low = Integer.valueOf(hex.substring(n + 1, n + 2), 16);
            data[i] = (byte) (height * 16 + low);
        }

        return data;
    }
}
