package me.mohistzh.tinyurl.service;

import me.mohistzh.tinyurl.util.TinyURLShortenUtil;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

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
    private Map<String, Long> strToLongMap = new ConcurrentHashMap<String, Long>();
    private Map<Long, String> longToStrMap = new ConcurrentHashMap<Long, String>();
    private volatile long incre = 1024L;

    @Override
    public String shortenURL(String urlInput) {
        if (!TinyURLShortenUtil.isValidURL(urlInput)) {
            throw new IllegalArgumentException("URL is invalid: " + urlInput);
        }
        try {
            URL url = new URL(urlInput);
            strToLongMap.computeIfAbsent(urlInput, k -> incre++);
            longToStrMap.computeIfAbsent(incre, k -> urlInput);
            String path = '/' + TinyURLShortenUtil.to62Digits(strToLongMap.get(urlInput));
            String result = new URL(url.getProtocol(), url.getHost(), path).toString();
            System.out.println(urlInput +" -> " + result);
            return result;

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
        String url = longToStrMap.get(id);
        if (url != null) {
            System.out.println(hashInput + " -> "+url);
        }
        return url;
    }
}
