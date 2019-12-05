package me.mohistzh.tinyurl.test;

import me.mohistzh.tinyurl.service.TinyURLService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * @Author Jonathan
 * @Date 2019/12/4
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
public class TinyURLServiceTest {

    @Autowired
    private TinyURLService tinyURLService;

    @Test
    public void testShortenURL() {
        tinyURLService.shortenURL("https://class.hujiang.com/category/514");
    }
    @Test
    public void testRecoverURL() {
        System.out.println(tinyURLService.recoverURL("1"));
    }


}
