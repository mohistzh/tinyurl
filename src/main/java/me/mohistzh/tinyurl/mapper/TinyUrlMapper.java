package me.mohistzh.tinyurl.mapper;


import me.mohistzh.tinyurl.model.TinyUrl;
import org.apache.ibatis.annotations.Param;

public interface TinyUrlMapper {

    int saveTinyUrl(@Param("po")TinyUrl tinyUrl);

    TinyUrl getTinyUrlById(@Param("id") long id);

}
