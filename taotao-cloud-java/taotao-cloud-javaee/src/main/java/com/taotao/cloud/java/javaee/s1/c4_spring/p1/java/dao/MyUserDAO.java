package com.taotao.cloud.java.javaee.s1.c4_spring.p1.java.dao;

public class MyUserDAO implements UserDAO{

    @Override
    public void deleteUser(Integer id) {
        System.out.println("hello world");
    }
}
