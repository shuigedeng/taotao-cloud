package com.taotao.cloud.java.javaee.s1.c10_ssm.java.service;

import com.github.pagehelper.PageInfo;
import com.taotao.cloud.java.javaee.s1.c10_ssm.java.pojo.User;

public interface UserService {

    PageInfo<User> getUserList(int page, int limit);

    void delteUserByIds(int[] ids);
}
