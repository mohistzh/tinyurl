package me.mohistzh.tinyurl.model;

import java.util.Date;

/**
 * RESTFul API response data model
 * @Author Jonathan
 * @Date 2019/12/5
 **/
public class APIResponse<T> {

    private int status;
    private String message;
    private T data;
    private Date time;

    public APIResponse() {}

    public APIResponse(int status, String message, T data) {
        this.status =status;
        this.data = data;
        this.message = message;
        this.setTime(new Date());
    }


    public static <V> APIResponse<V> failure(int status, String message, V result) {
        return new APIResponse<V>(status, message, result);
    }

    public static <V> APIResponse<V> failure(String message) {
        APIResponse<V> result = new APIResponse<V>();
        result.setMessage(message);
        result.setStatus(-1);
        return result;
    }

    public static <V> APIResponse<V> failure(int status, String message) {
        APIResponse<V> result = new APIResponse<V>();
        result.setStatus(status);
        result.setMessage(message);
        return result;
    }

    public static <V> APIResponse<V> success(V data) {
        APIResponse<V> result = new APIResponse<V>();
        result.setStatus(0);
        result.setMessage("success");
        result.setData(data);
        return result;
    }

    public static <V> APIResponse<V> success(String message, V data) {
        APIResponse<V> result = new APIResponse<V>();
        result.setMessage(message);
        result.setData(data);
        return result;
    }

    public static <V> APIResponse<V> success(Integer status,String message, V data) {
        APIResponse<V> result = new APIResponse<V>();
        result.setStatus(status);
        result.setMessage(message);
        result.setData(data);
        result.setTime(new Date());
        return result;
    }
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }



}
