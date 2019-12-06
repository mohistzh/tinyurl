package me.mohistzh;

import me.mohistzh.tinyurl.util.TinyURLShortenUtil;
import org.junit.Test;

/**
 * @Author Jonathan
 * @Date 2019/12/4
 **/
public class TinyURLShortenUtilTest {

    //@Test
    public void testHashString() {
        System.out.println(TinyURLShortenUtil.to62Digits(TinyURLShortenUtil.toLong("google")));
        System.out.println(TinyURLShortenUtil.to62Digits(TinyURLShortenUtil.toLong("baidudsadsdsadsa")));
    }

    //@Test
    public void testIsValidURL() {
        assert TinyURLShortenUtil.isValidURL("https://google.com");
        assert TinyURLShortenUtil.isValidURL("https:/google.com");

    }

    @Test
    public void testEncodedParameter() throws Exception{
        System.out.println(TinyURLShortenUtil.endcodeParameters("https%3A%2F%2Fwww.paypal.com%2Fwebscr%3Fcmd%3D_express-checkout%26token%3DEC-4ML87366PX760390P"));
    }
}
