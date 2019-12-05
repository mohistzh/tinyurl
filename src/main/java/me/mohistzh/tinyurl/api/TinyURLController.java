package me.mohistzh.tinyurl.api;

import lombok.extern.slf4j.Slf4j;
import me.mohistzh.tinyurl.model.APIResponse;
import me.mohistzh.tinyurl.model.TinyUrlResponse;
import me.mohistzh.tinyurl.service.TinyURLService;
import me.mohistzh.tinyurl.util.TinyURLShortenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 *
 *
 * @Author Jonathan
 * @Date 2019/12/5
 **/
@Slf4j
@Controller
public class TinyURLController {

    @Autowired
    TinyURLService tinyURLService;

    @ResponseBody
    @CrossOrigin("*")
    @RequestMapping(value = "/shorten", method = RequestMethod.POST)
    public APIResponse<Object> shortenTinyUrl(@RequestParam("url") String originalUrl) {

        if (!TinyURLShortenUtil.isValidURL(originalUrl)) {
            return APIResponse.failure("Invalid url format");
        }
        String decoratedUrl = tinyURLService.shortenURL(originalUrl);
        return APIResponse.success(TinyUrlResponse.of(originalUrl, decoratedUrl));
    }

    @ResponseBody
    @RequestMapping(value = "/recover", method = RequestMethod.POST)
    public APIResponse<Object> recoverTinyUrl(String path) {
        String originalUrl = tinyURLService.recoverURL(path);
        if (originalUrl == null) {
            return APIResponse.failure("Path "+path + " doesn't exists");
        }
        return APIResponse.success(originalUrl);

    }

}
