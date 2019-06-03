package my.loong.mudel.ehcache;

import com.loong.commons.ehcache.utils.EhcacheUtil;
import org.junit.Test;

public class TestEhcache {

    @Test
    public void test1(){
        EhcacheUtil ehcacheUtil=EhcacheUtil.getInstance();
        ehcacheUtil.put("userCache","admin","123");
        System.out.println(ehcacheUtil.get("userCache", "admin"));
    }
}
