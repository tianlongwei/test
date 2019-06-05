package com.loong.commons.security;

import org.apache.shiro.codec.Hex;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class Digest {
    private static String SHA_1="SHA-1";
    private static String MD5="MD5";
    private static Integer SALT_SIZE=8;
    private static SecureRandom random=new SecureRandom();
    /**
     * 生成随机盐值
     */
    public static byte[] generateSalt(){
        byte[] salt=new byte[SALT_SIZE];
        random.nextBytes(salt);
        return salt;
    }

    /**
     * md5、sha1散列算法
     */
    public static byte[] digest(String algorithm,byte[] source,byte[] salt,int iterations){
        byte[] result=null;
        try {
            MessageDigest digest=MessageDigest.getInstance(algorithm);
            if (salt!=null){
                /**
                 * 将盐值添加到特定的byte数组中
                 */
                digest.update(salt);//只有第一次hash时加盐
            }
            result=digest.digest(source);

            for (int i = 1; i < iterations; i++) {
                digest.reset();
                result=digest.digest(result);
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * MD5散列算法
     */
    public static byte[] MD5(byte[] source){
        return digest(MD5,source,null,1);
    }
    public static byte[] MD5(byte[] source,int iterations){
        return digest(MD5,source,null,iterations);
    }
    public static byte[] MD5(byte[] source,byte[] salt){
        return digest(MD5,source,salt,1);
    }
    public static byte[] MD5(byte[] source,byte[] salt,int iterations){
        return digest(MD5,source,salt,iterations);
    }

    /**
     * SHA_1散列算法
     */
    public static byte[] SHA1(byte[] source){
        return digest(SHA_1,source,null,1);
    }
    public static byte[] SHA1(byte[] source,int iterations){
        return digest(SHA_1,source,null,iterations);
    }
    public static byte[] SHA1(byte[] source,byte[] salt){
        return digest(SHA_1,source,salt,1);
    }
    public static byte[] SHA1(byte[] source,byte[] salt,int iterations){
        return digest(SHA_1,source,salt,iterations);
    }

    /**
     * 生成密文密码
     */
    public static String generatePwd(String password){
        byte[] salt=generateSalt();
        byte[] pwd = Digest.SHA1(password.getBytes(), salt, 1024);
        return Hex.encodeToString(salt)+Hex.encodeToString(pwd);
    }

    /**
     * 验证密码是否正确
     */
    public static boolean validationPwd(String password,String pwd){
        byte[] salt=generateSalt();
        byte[] pwd1 = Digest.SHA1(password.getBytes(), salt, 1024);
        String s = Hex.encodeToString(salt) + Hex.encodeToString(pwd1);
        return s.equals(pwd);
    }

}
