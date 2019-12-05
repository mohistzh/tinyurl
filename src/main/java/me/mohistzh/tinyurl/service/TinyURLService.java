package me.mohistzh.tinyurl.service;


public interface TinyURLService {

    public String shortenURL(String urlInput);

    public String recoverURL(String hashInput);
}
