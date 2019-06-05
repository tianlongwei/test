package my.loong.mudel.system;

import com.loong.commons.security.Digest;
import org.apache.shiro.codec.Hex;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class TestSys {

    @Test
    public void tets() throws UnsupportedEncodingException {
        byte[] bytes = "东".getBytes();
        for (byte a:bytes
             ) {
            System.out.println(a);
        }

        Integer aaa=new Integer(345345);
    }
    //a 97 b 98 c 99 d 100
    //97 100 109 105 110

    @Test
    public void test1(){
        //
        try {
            MessageDigest md5 = MessageDigest.getInstance("Md5");
            //admin 转化为byte
            byte[] digest = md5.digest("admin".getBytes());
            for (byte a:digest
                 ) {
                System.out.println(a);
            }

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test2() throws UnsupportedEncodingException {
        SecureRandom secureRandom=new SecureRandom();
        byte[] bytes=new byte[16];
        secureRandom.nextBytes(bytes);
        for (byte a:bytes
             ) {
            System.out.println(a);
        }
        //16个字节  一个字节8位
        //需要转化为16进制
        //117104-9861125-114-80-59124-707147-17-59-97-35

        /*
        *  -87
            48
            -51
            80
            -83
            6
            99
            -65
            -100
            -91
            112
            31
            126
            28
            90
            58
        * */
        String str=new String(bytes);
        System.out.println(str);
        System.out.println("------------------------------");
        char[] encode = Hex.encode(bytes);
        for (char c:encode
             ) {
            System.out.print(c);//a930cd50ad0663bf9ca5701f7e1c5a3a
        }
    }


    @Test
    public void testByte(){
        SecureRandom random=new SecureRandom();
        byte[] bytes=new byte[16];
        random.nextBytes(bytes);
        String s = Hex.encodeToString(bytes);
        System.out.println(s);
    }

    @Test
    public void testMd5(){
        byte[] bytes = Digest.MD5("admin".getBytes(),"admin".getBytes(),2);
        System.out.println(bytes.length);
        //f6fdffe48c908deb0f4c3bd36c032e72--不加盐hash ec15d79e36e14dd258cfff3d48b73d35
        //f6fdffe48c908deb0f4c3bd36c032e72--加盐hash   af2a9e342cf4c662c9999d0e05819b13

        //hash2次： af2a9e342cf4c662c9999d0e05819b13
        //         3ef7164d1f6167cb9f2658c07d3c2f0a
        System.out.println(Hex.encodeToString(bytes));
        System.out.println("===========");
    }

    @Test
    public void testSha(){
        byte[] salt = Digest.generateSalt();
        System.out.println(Hex.encodeToString(salt));//8e90dfe25e97298be563c9c66a8f1967495d2b40169295b964446c4a
    }

    @Test
    public void testSAH1(){
        byte[] bytes = Digest.SHA1("1234".getBytes(),Hex.decode("8e90dfe25e97298b"),1024);
        System.out.println(bytes.length);
        System.out.println(Hex.encodeToString(bytes));//e563c9c66a8f1967495d2b40169295b964446c4a
        //e563c9c66a8f1967495d2b40169295b964446c4a
        //8e90dfe25e97298b
        System.out.println("===========");//8e90dfe25e97298be563c9c66a8f1967495d2b40169295b964446c4a
    }

    @Test
    public void testSimhash(){
        String algorithmName="SHA-1";
        ByteSource source=ByteSource.Util.bytes("1234");
        System.out.println(source);
        ByteSource salt=ByteSource.Util.bytes(Hex.decode("8e90dfe25e97298b"));
        System.out.println(salt);
        int hsahIterations=1024;
        SimpleHash hash=new SimpleHash(algorithmName,source,salt,hsahIterations);
        System.out.println(hash);//7785e525c6ed22bcb82b0fa72df1bfa6c22aba78
    }

}
