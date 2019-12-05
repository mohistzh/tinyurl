package me.mohistzh.tinyurl.service;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import lombok.extern.slf4j.Slf4j;
import me.mohistzh.tinyurl.mapper.TinyUrlMapper;
import me.mohistzh.tinyurl.model.TinyUrl;
import me.mohistzh.tinyurl.util.TinyURLShortenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * To shorten and recover url
 * @Author Jonathan
 * @Date 2019/12/4
 **/
@Slf4j
@Service
public class TinyURLServiceImpl implements TinyURLService {

    @Autowired
    TinyUrlMapper tinyUrlMapper;

    @Override
    public String shortenURL(String urlInput) {
        if (!TinyURLShortenUtil.isValidURL(urlInput)) {
            throw new IllegalArgumentException("URL is invalid: " + urlInput);
        }
        try {
            URL url = new URL(urlInput);
            TinyUrl tinyUrl = tinyUrlMapper.getTinyUrlByUrl(urlInput);
            if (tinyUrl == null) {
                tinyUrl = TinyUrl.of(urlInput);
                tinyUrlMapper.saveTinyUrl(tinyUrl);
            }
            String path = '/' + TinyURLShortenUtil.to62Digits(tinyUrl.getId());
            String result = new URL(url.getProtocol(), url.getHost(), path).toString();
            System.out.println(urlInput +" -> " + result);
            return result;

        } catch (MalformedURLException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Failed to shorten url"+ urlInput);
        }

    }

    private LoadingCache<Long, TinyUrl> cachedTinyUrl = CacheBuilder.newBuilder().maximumSize(100_000)
            .expireAfterWrite(10, TimeUnit.MINUTES).build(new CacheLoader<Long, TinyUrl>() {
                @Override
                public TinyUrl load(Long id) throws Exception {
                    TinyUrl url = tinyUrlMapper.getTinyUrlById(id);
                    if (url != null) {
                        return url;
                    }
                    return TinyUrl.of(null);
                }
            });
    @Override
    public String recoverURL(String hashInput) {
        if (hashInput == null || "".equals(hashInput)) {
            throw new IllegalArgumentException("The given hash input can not be empty");
        }
        try {
            long id = TinyURLShortenUtil.toLong(hashInput);

            TinyUrl tinyUrl = cachedTinyUrl.get(id);
            return tinyUrl.getUrl();
        } catch (ExecutionException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
