package com.taotao.cloud.java.javaee.s1.c11_web.java.service;

import com.github.pagehelper.PageInfo;
import com.taotao.cloud.java.javaee.s1.c11_web.java.pojo.UserToken;

public interface UserTokenService {
    PageInfo<UserToken> getTokenList(UserToken criteria, int page, int pageSize);

    UserToken getTokenById(int id);

    void updateToken(UserToken token);

    void addToken(UserToken token);

    void deleteUserToken(int[] ids);
}
