package com.kaixiang.module.common.encoder;

import static java.nio.charset.StandardCharsets.UTF_8;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.GeneralSecurityException;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * @Author kaixiang.tao
 * @Date 2021/12/4
 */
@Component
public class BlogAesPasswordEncoder {

    @Value("${blog.aes.iv}")
    private String iv = "test1234";

    private static final String AES = "AES";
    private static final String ENCRYPT_ALGO = "AES/GCM/NoPadding";
    private static final int TAG_LENGTH_BIT = 128;

    public String encrypt(String content, String key) throws GeneralSecurityException {
        SecretKey secretKey = generateKey(key);
        Cipher cipher = Cipher.getInstance(ENCRYPT_ALGO);

        GCMParameterSpec spec = new GCMParameterSpec(TAG_LENGTH_BIT, iv.getBytes(UTF_8));
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, spec);

        byte[] encryptData = cipher.doFinal(content.getBytes());
        return Base64.getEncoder().encodeToString(encryptData);
    }

    public String decrypt(String content, String key) throws GeneralSecurityException {
        SecretKey secretKey = generateKey(key);
        Cipher cipher = Cipher.getInstance(ENCRYPT_ALGO);

        GCMParameterSpec spec = new GCMParameterSpec(TAG_LENGTH_BIT, iv.getBytes(UTF_8));
        cipher.init(Cipher.DECRYPT_MODE, secretKey, spec);

        byte[] message = Base64.getDecoder().decode(content);
        byte[] decryptData = cipher.doFinal(message);
        return new String(decryptData, UTF_8);
    }

    private SecretKey generateKey(String password) {
        return new SecretKeySpec(password.getBytes(UTF_8), AES);
    }
}
