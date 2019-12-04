package me.mohistzh.tinyurl.pojo;


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


}
