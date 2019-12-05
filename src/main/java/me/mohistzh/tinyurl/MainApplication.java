package me.mohistzh.tinyurl;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author Jonathan
 * @Date 2019/12/4
 **/
@SpringBootApplication
@MapperScan("me.mohistzh.tinyurl.mapper")
public class MainApplication {

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }
}
