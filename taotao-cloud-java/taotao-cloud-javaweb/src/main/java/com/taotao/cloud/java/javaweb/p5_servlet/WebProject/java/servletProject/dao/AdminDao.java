package com.taotao.cloud.java.javaweb.p5_servlet.WebProject.java.servletProject.dao;


import com.taotao.cloud.java.javaweb.p5_servlet.WebProject.java.servletProject.entity.Admin;
import java.util.List;

public interface AdminDao {
    public int insert(Admin admin);
    public int delete(String username);
    public int update(Admin admin);
    public Admin select(String username);
    public List<Admin> selectAll();
}
