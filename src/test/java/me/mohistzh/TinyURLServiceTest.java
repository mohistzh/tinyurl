package me.mohistzh;

import me.mohistzh.tinyurl.service.TinyURLService;
import me.mohistzh.tinyurl.service.TinyURLServiceImpl;
import org.junit.Before;
import org.junit.Test;

/**
 * @Author Jonathan
 * @Date 2019/12/4
 **/
public class TinyURLServiceTest {

    TinyURLService tinyURLService;

    @Before
    public void init() {
        tinyURLService = new TinyURLServiceImpl();
    }
    @Test
    public void testShortenURL() {
        tinyURLService.shortenURL("https://google.com");
        tinyURLService.shortenURL("https://github.com/404");
        tinyURLService.shortenURL("https://www.reddit.com/r/PewdiepieSubmissions/");
    }


}
