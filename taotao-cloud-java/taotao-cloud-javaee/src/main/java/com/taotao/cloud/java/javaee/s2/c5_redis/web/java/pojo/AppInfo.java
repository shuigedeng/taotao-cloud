package com.taotao.cloud.java.javaee.s2.c5_redis.web.java.pojo;

import lombok.Data;

@Data
public class AppInfo {
    private Integer id;
    private String corpName;
    private String appName;
    private String appKey;
    private String appSecret;
    private String redirectUrl;
    private Integer limit;
    private String description;
    private Integer cusId;
    private Integer state;
}
