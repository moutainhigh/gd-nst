/**
 * RSAUtil.java
 *
 * Copyright 2011 Baidu, Inc.
 *
 * Baidu licenses this file to you under the Apache License, version 2.0
 * (the "License"); you may not use this file except in compliance with the
 * License.  You may obtain a copy of the License at:
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package cn.gdeng.nst.util.server;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.ShortBufferException;

/**
 * RSAUtil是一个运用RSA加密算法进行签名和加密、解密的工具类，百度API专用
 * @author yangjj
 */
public class RSAUtil {
    public static final String KEY_ALGORITHM = "RSA";

    /**
     * 用公钥解密通过私钥加密的数据
     * 
     * @param data 通过私钥加密的数据
     * @param key 用来解密的公钥
     * @return 解密后的数据
     * 
     * @throws NoSuchAlgorithmException 假如用户的JDK不支持RSA
     * @throws InvalidKeySpecException 假如根据privateKey生成密钥失败
     * @throws InvalidKeyException 假如输入的RSA私钥不合法
     * @throws SignatureException 假如根据privateKey生成密钥失败
     * @throws UnsupportedEncodingException 假如privateKey不是使用UTF-8进行编码
     * @throws NoSuchPaddingException 假如产生的密钥对有问题
     * @throws BadPaddingException 假如输入的加密的数据填充数据错误
     * @throws IllegalBlockSizeException 假如输入的加密的数据字节数不是BlockSize的整数倍
     * @throws ShortBufferException 
     */
    public static byte[] decryptByPublicKey(byte[] data, Key publicKey) throws NoSuchAlgorithmException, InvalidKeySpecException,
            InvalidKeyException, SignatureException, UnsupportedEncodingException, NoSuchPaddingException, 
            IllegalBlockSizeException, BadPaddingException, ShortBufferException {
        // 取得公钥
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);

        // 对数据解密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, publicKey);

        return CipherUtil.process(cipher, 128, data);
    }

    /**
     * 用公钥加密数据
     * 
     * @param data 等待加密的原始数据
     * @param key 用来加密的公钥
     * @return 加密后的数据
     * 
     * @throws NoSuchAlgorithmException 假如用户的JDK不支持RSA
     * @throws InvalidKeySpecException 假如根据privateKey生成密钥失败
     * @throws InvalidKeyException 假如输入的RSA私钥不合法
     * @throws SignatureException 假如根据privateKey生成密钥失败
     * @throws UnsupportedEncodingException 假如privateKey不是使用UTF-8进行编码
     * @throws NoSuchPaddingException 假如产生的密钥对有问题
     * @throws BadPaddingException 假如输入的加密的数据填充数据错误
     * @throws IllegalBlockSizeException 假如输入的加密的数据字节数不是BlockSize的整数倍
     * @throws ShortBufferException 
     */
    public static byte[] encryptByPublicKey(byte[] data, Key publicKey) throws NoSuchAlgorithmException, InvalidKeySpecException,
            InvalidKeyException, SignatureException, UnsupportedEncodingException, NoSuchPaddingException, 
            IllegalBlockSizeException, BadPaddingException, ShortBufferException {
        // 取得公钥
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);

        // 对数据加密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);

        return CipherUtil.process(cipher, 117, data);
    }

    /**
     * 取得公钥
     * 
     * @param keyMap 密钥对Map
     * @return 公钥
     * @throws UnsupportedEncodingException 
     * @throws InvalidKeySpecException 
     * @throws NoSuchAlgorithmException 
     */
    public static Key getPublicKey(String key) throws NoSuchAlgorithmException, UnsupportedEncodingException, InvalidKeySpecException {
        // 解密由base64编码的公钥
        byte[] keyBytes = Base64.decode(key);

        // 构造X509EncodedKeySpec对象
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);

        // KEY_ALGORITHM 指定的加密算法
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);

        // 取公钥匙对象
        return keyFactory.generatePublic(keySpec);
    }
    
}
