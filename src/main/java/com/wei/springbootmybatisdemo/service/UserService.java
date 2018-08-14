package com.wei.springbootmybatisdemo.service;

import com.wei.springbootmybatisdemo.model.User;

import java.util.List;

public interface UserService {

    int insertUser(User user) throws Exception;

    public List<User> findAllUser(int pageNum, int pageSize);

    User getBykeys(Integer id);

    public List<User> getAll() throws Exception;
}
