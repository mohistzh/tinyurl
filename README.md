# TinyURL service

> TinyURL is a URL shortening web service, which provides short aliases for redirection of long URLs.


## Example

The service provides two Web APIs to shorten or recover url by settings. The decorated url uses 62-base characters(a~z,A~Z,0~9) as their fundamental digitals which means it supports 916,132,832 links with 5 digitals, no worries about the range. 

**URL shortening**

```$xslt
curl -d 'url=https://news.google.com/?hl=en-PH' -H 'Content-Type:application/x-www-form-urlencoded' localhost:8964/shorten | json_pp
```
```$xslt
{
   "message" : "success",
   "data" : {
      "decoratedUrl" : "http://localhost:8964/1",
      "originalUrl" : "https://news.google.com/?hl=en-PH"
   },
   "status" : 0,
   "time" : "2019-12-06T08:35:37.171+0000"
}
```

**URL recover**

```$xslt
curl -d 'path=1' -H 'Content-Type:application/x-www-form-urlencoded' localhost:8964/recover | json_pp
```
```$xslt
{
   "status" : 0,
   "message" : "success",
   "data" : "https://news.google.com/?hl=en-PH",
   "time" : "2019-12-06T08:38:10.406+0000"
}
```
