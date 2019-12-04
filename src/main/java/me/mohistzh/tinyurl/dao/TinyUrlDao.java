package me.mohistzh.tinyurl.dao;


import me.mohistzh.tinyurl.pojo.TinyUrl;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface TinyUrlDao {

    int saveTinyUrl(@Param("po")TinyUrl tinyUrl);

    TinyUrl getTinyUrlById(@Param("id") long id);

}
