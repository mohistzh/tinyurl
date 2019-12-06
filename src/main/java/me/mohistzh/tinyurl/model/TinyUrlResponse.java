package me.mohistzh.tinyurl.model;

import lombok.Data;

/**
 * @Author Jonathan
 * @Date 2019/12/5
 **/
@Data
public class TinyUrlResponse {

    private String originalUrl;

    private String decoratedUrl;

    public static TinyUrlResponse of(String originalUrl, String decoratedUrl) {
        TinyUrlResponse response = new TinyUrlResponse();
        response.setOriginalUrl(originalUrl);
        response.setDecoratedUrl(decoratedUrl);
        return response;
    }
}
