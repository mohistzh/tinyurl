package me.mohistzh.tinyurl.util;

import me.mohistzh.tinyurl.model.TinyUrl;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Map;
import java.util.List;

/**
 * A utility static class to generate shorten url from given URL path.
 *
 * @Author Jonathan
 * @Date 2019/12/4
 **/
public class TinyURLShortenUtil {

    private static final char[] letters = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C',
            'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X',
            'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's',
            't', 'u', 'v', 'w', 'x', 'y', 'z' };


    /**
     * Base 62 numbers of characters (including 0~9,A~Z,a~z)
     * @param input
     * @return
     */
    public static String to62Digits(final long input) {
        StringBuilder stringBuilder = new StringBuilder();
        long remain = input;
        do {
            int index =(int) remain % 62;
            stringBuilder.append(letters[index]);
            remain = remain / 62;
        } while (remain > 0);

        return stringBuilder.reverse().toString();
    }

    /**
     * Recover identity from given hash(string)
     * @param input
     * @return
     */
    public static long toLong(final String input) {
        byte[] bytes = input.getBytes();
        long result = 0L;
        for (byte bt : bytes) {
            if (bt >= 'A' && bt <= 'Z') {
                result = (result * 62) + (bt - 55);
            } else if (bt >= 'a' && bt <= 'z') {
                result = (result * 62) + (bt - 61);
            } else if (bt >= '0' && bt <= '9') {
                result = (result * 62) + (bt - 48);
            } else {
                throw new IllegalArgumentException("Occurs illegal character: "+ (char) bt);
            }
        }
        return result;

    }

    public static String endcodeParameters(String originalUrl) throws Exception{

        URI uri = new URI(originalUrl);
        MultiValueMap<String, String> parameterMap = UriComponentsBuilder.fromUri(uri).build().getQueryParams();
        UriComponentsBuilder builder = UriComponentsBuilder.newInstance().scheme(uri.getScheme()).host(uri.getHost()).port(uri.getPort()).path(uri.getPath()).fragment(uri.getRawFragment());
        for (Map.Entry<String, List<String>> entry : parameterMap.entrySet()) {
            String key = entry.getKey();
            if (isEncoded(key)) {
                key = URLEncoder.encode(key, StandardCharsets.UTF_8.name());
            }
            List<String> values = new ArrayList<String>(entry.getValue().size());
            for (String value  : entry.getValue()) {
                if (value == null) {
                    continue;
                }
                String newValue = value;
                if (isEncoded(value)) {
                    newValue = URLEncoder.encode(value, StandardCharsets.UTF_8.name());
                }
                values.add(newValue);
            }

            builder.queryParam(key, values.toArray());

        }
        return builder.build().toUriString();

    }


    public static boolean isEncoded(String input) {
        return isEncoded(input, StandardCharsets.UTF_8);
    }

    public static boolean isEncoded(String input, Charset charset) {
        charset = charset == null? StandardCharsets.UTF_8 : charset;
        try {
            String decoded = URLDecoder.decode(input, charset.name());
            return !decoded.equals(input);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e.getCause());
        } catch (Exception e) {
            return false;
        }



    }

    /**
     * Check whether is valid url
     * @param urlString
     * @return
     */
    public static boolean isValidURL(String urlString) {
        if (urlString == null || "".equals(urlString)) {
            return false;
        }
        URI uri = null;
        try {
             uri = new URI(urlString);
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return false;
        }

        return uri.getHost() != null && (uri.getScheme().equalsIgnoreCase("http") || uri.getScheme().equalsIgnoreCase("https"));


    }

}
