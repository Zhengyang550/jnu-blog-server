package com.jnu.example.core.config.security;

import com.jnu.example.core.util.JnuEncryptUtil;
import org.springframework.security.crypto.password.PasswordEncoder;


/**
 *  @Author: zy
 *  @Date: 2020/4/15 21:37
 *  @Description: 自定义加密方式
 */
public class CustomizedPasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence charSequence) {
        return JnuEncryptUtil.encryptToBase64((String)charSequence);
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return encodedPassword.equals(JnuEncryptUtil.encryptToBase64((String)rawPassword));
    }

    public String decode(String password){
        return JnuEncryptUtil.decryptFromBase64(password);
    }
}
