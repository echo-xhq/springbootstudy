package com.example.springbootstudy.token.ssoUtils;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Base64Utils;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Echo
 * @version 1.0
 * @description Create by 2022/7/19 17:22
 */
public class AesUtil {

    /**
     * 默认的字符编码
     */
    private static final String DEFAULT_CHARSET = "UTF-8";

    /**
     * 算法
     */
    private static String ALGORITHM = "AES";


    /**
     * 算法/模式/填充
     **/
    private static final String CipherMode = "AES/ECB/PKCS5Padding";


    /**
     * 记录日志
     **/
    private final static Logger logger = LoggerFactory.getLogger(AesUtil.class);

    public static SecretKey geneKey(String oriKey) throws Exception {

        //获取一个密钥生成器实例
        //logger.debug("生成AES密钥器");
        KeyGenerator keyGenerator = KeyGenerator.getInstance(ALGORITHM);
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
        //logger.debug("设置加密用的种子，密钥");
        random.setSeed(oriKey.getBytes());//设置加密用的种子，密钥
        //logger.debug("初始化SecureRandom");
        keyGenerator.init(random);
        //logger.debug("生成secretKey");
        SecretKey secretKey = keyGenerator.generateKey();
        return secretKey;
    }

    public static byte[] aes(byte[] contentArray,int mode,SecretKey secretKey) throws Exception{
        //logger.debug("Ciper获取解密实例"+ALGORITHM);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        //logger.debug("初始化解密流程"+ALGORITHM);
        //logger.debug("密码处理模式 1加密 2解密：当前模式："+mode);
        cipher.init(mode,secretKey);//1加密 2解密
        //logger.debug("将content变成字符串");
        String contentStr = new String(contentArray,DEFAULT_CHARSET);
        //logger.debug("转成字符串后"+contentStr);
        //logger.debug("Base64Utils解密字符串");
        if(mode==1){
            //logger.debug("cipher加密");
            byte[] result = cipher.doFinal(contentArray);
            String bytes = Base64Utils.encodeToUrlSafeString(result);
            return bytes.getBytes(DEFAULT_CHARSET);
        }else if (mode==2){
            byte[] bytes = Base64Utils.decodeFromUrlSafeString(contentStr);
            //logger.debug("cipher解密");
            byte[] result = cipher.doFinal(bytes);
            return result;
        }else {
            return null;
        }

    }


    /**
     * 解密AES 32位
     *
     * @param sSrc      解密的内容
     * @param secretKey 秘钥
     * @return 解密后的明文 数据
     */
    public static byte[] decrypt(String sSrc, SecretKey secretKey) throws Exception {
        //logger.debug("开始AES解密方法");
        if (secretKey == null) {
            logger.error("需要加密的秘钥为空");
            return null;
        }
        return aes(sSrc.getBytes(DEFAULT_CHARSET), Cipher.DECRYPT_MODE,secretKey);
    }

    /**
     * 加密
     * @param content 待加密串
     * @param key   加密密匙
     * @return  密串
     */
    public static String encrypt(String content, String key){
        try {
            //创建密码器
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            byte[] bytecontent = content.getBytes("utf-8");
            //初始化加密器为加密模式
            cipher.init(Cipher.ENCRYPT_MODE,geneKey(key));
            //对目标执行加密操作
            byte[] result = cipher.doFinal(bytecontent);
            //对密串执行Base64-加盐
            return Base64Utils.encodeToUrlSafeString(result);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) throws Exception {

        String key = "43dd0c6b845b01c420603c5fc2bbd3b7";

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("protype","1");
        jsonObject.put("token","eyJhbGciOiJIUzUxMiJ9.eyJleHAiOjE1OTUzMTM5MjQsInN1YiI6IjE4ODY3MTAxMjE5IiwiaWF0IjoxNTk1MzEyMTI0NDgzfQ.TJ2jSBp-qESCyq8CWGxgjOSwCxtVY1tMDYSXujc84ovaAuvwHTDZsztUVskkJxkotUIbGlnBGxseNnR-GgSEJg");

        //将requestBody中的数据加密
        String s = encrypt(jsonObject.toString(),key);

        System.out.println(s);

        //将加密后的数据解密
        byte[] s1 = decrypt(s,geneKey(key));

        String s2 = new String(s1,"utf-8");

        JSONObject jsonObject1 = JSONObject.parseObject(s2);

        System.out.println("protype= "+jsonObject1.get("protype"));
        System.out.println("token= "+jsonObject1.get("token"));

        System.out.println("加密后的字节数据："+new String(s1,"utf-8"));

    }
}
