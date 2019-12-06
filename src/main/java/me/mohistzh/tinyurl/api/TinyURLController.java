package me.mohistzh.tinyurl.api;

import lombok.extern.slf4j.Slf4j;
import me.mohistzh.tinyurl.model.APIResponse;
import me.mohistzh.tinyurl.model.TinyUrlResponse;
import me.mohistzh.tinyurl.service.TinyURLService;
import me.mohistzh.tinyurl.util.TinyURLShortenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;

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

    private Resource redirectPage = new ClassPathResource("static/redirect.html");

    /***
     * To shorten original long url
     * @param originalUrl
     * @return
     */
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

    /**
     * Recover long original url by given path
     * @param path
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/recover", method = RequestMethod.POST)
    public APIResponse<Object> recoverTinyUrl(String path) {
        String originalUrl = tinyURLService.recoverURL(path);
        if (originalUrl == null) {
            return APIResponse.failure("Path "+path + " doesn't exists");
        }
        return APIResponse.success(originalUrl);

    }

    /**
     * Redirect to the original url, before done that I added a redirect page in order to do something.
     * @param path
     * @param response
     */
    @RequestMapping(value = "{path}", method = RequestMethod.GET)
    public void redirectUrl(@PathVariable("path") String path, HttpServletResponse response) throws Exception{

        String originalUrl = tinyURLService.recoverURL(path);
        if (originalUrl != null) {
            String finalUrl = TinyURLShortenUtil.endcodeParameters(originalUrl);
            String content = StreamUtils.copyToString(redirectPage.getInputStream(), StandardCharsets.UTF_8);
            content = content.replace("$(VALUE)", finalUrl);
            StreamUtils.copy(content.getBytes(StandardCharsets.UTF_8), response.getOutputStream());
        } else {
            response.sendError(404, "Page not found");
        }
    }

}
