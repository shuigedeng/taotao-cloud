package com.taotao.cloud.java.javaee.s1.c3_mybatis.hellomybatis.java.dao;

import com.taotao.cloud.java.javaee.s1.c3_mybatis.hellomybatis.java.entity.Passenger;
import org.apache.ibatis.annotations.Param;

public interface PassengerDAO {
    Passenger queryPassengerById(@Param("id") Integer id);
}
