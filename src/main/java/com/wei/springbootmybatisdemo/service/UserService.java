package com.wei.springbootmybatisdemo.service;

import com.wei.springbootmybatisdemo.model.User;

import java.util.List;

public interface UserService {

    int insertUser(User user);

    public List<User> findAllUser(int pageNum, int pageSize);
}
