package com.taotao.cloud.java.javaee.s2.c5_redis.web.java.service;

import com.github.pagehelper.PageInfo;
import com.taotao.cloud.java.javaee.s2.c5_redis.web.java.pojo.ApiMapping;

public interface ApiMappingService {
    void addApiMapping(ApiMapping mapping);

    void updateApiMapping(ApiMapping mapping);

    PageInfo<ApiMapping> getMappingList(ApiMapping criteria, int page, int limit);

    ApiMapping getMappingById(int id);

    void deleteMapping(int[] ids);
}
