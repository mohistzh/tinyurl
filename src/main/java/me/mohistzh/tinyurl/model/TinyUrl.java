package me.mohistzh.tinyurl.model;


import lombok.Data;

import java.sql.Timestamp;

/**
 * @Author Jonathan
 * @Date 2019/12/4
 **/
@Data
public class TinyUrl {

    private long id;
    private String url;
    private Timestamp createdAt;

    public static TinyUrl of(String url) {
        TinyUrl tinyUrl = new TinyUrl();
        tinyUrl.url = url;
        return tinyUrl;
    }


}
