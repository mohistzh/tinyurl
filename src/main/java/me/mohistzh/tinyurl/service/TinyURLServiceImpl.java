package me.mohistzh.tinyurl.service;

import me.mohistzh.tinyurl.util.TinyURLShortenUtil;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * To shorten and recover url
 * @Author Jonathan
 * @Date 2019/12/4
 **/
public class TinyURLServiceImpl implements TinyURLService {


    /**
     * TODO there should be use database to persist URL, long value is designed for associating with URL
     * e.g.
     *
     * long id (increment)
     * varchar url
     */
    private Map<String, Long> strToLongMap = new HashMap<String, Long>();
    private Map<Long, String> longToStrMap = new HashMap<Long, String>();
    private volatile long incre = 0L;

    @Override
    public String shortenURL(String urlInput) {
        if (urlInput == null || "".equals(urlInput)) {
            throw new IllegalArgumentException("URL can not be empty");
        }
        try {
            URL url = new URL(urlInput);
            strToLongMap.computeIfAbsent(urlInput, k -> incre++);
            longToStrMap.computeIfAbsent(incre, k -> urlInput);
            String path = '/' + TinyURLShortenUtil.to62Digits(strToLongMap.get(urlInput));
            return new URL(url.getProtocol(), url.getHost(), path).toString();

        } catch (MalformedURLException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Failed to shorten url"+ urlInput);
        }

    }

    @Override
    public String recoverURL(String hashInput) {
        if (hashInput == null || "".equals(hashInput)) {
            throw new IllegalArgumentException("The given hash input can not be empty");
        }
        long id = TinyURLShortenUtil.toLong(hashInput);
        return longToStrMap.get(id);
    }
}
