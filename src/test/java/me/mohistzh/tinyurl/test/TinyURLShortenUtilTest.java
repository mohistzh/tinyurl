package me.mohistzh.tinyurl.test;

import me.mohistzh.tinyurl.util.TinyURLShortenUtil;
import org.junit.Test;

/**
 * @Author Jonathan
 * @Date 2019/12/4
 **/
public class TinyURLShortenUtilTest {

    @Test
    public void testHashString() {
        System.out.println(TinyURLShortenUtil.to62Digits(TinyURLShortenUtil.toLong("google")));
        System.out.println(TinyURLShortenUtil.to62Digits(TinyURLShortenUtil.toLong("baidudsadsdsadsa")));
    }

    @Test
    public void testIsValidURL() {
        assert TinyURLShortenUtil.isValidURL("https://google.com");
        assert TinyURLShortenUtil.isValidURL("https:/google.com");

    }
}
