package com.wei.springbootmybatisdemo.service.impl;

import com.fasterxml.classmate.types.ResolvedRecursiveType;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wei.springbootmybatisdemo.mapper.UserMapper;
import com.wei.springbootmybatisdemo.model.User;
import com.wei.springbootmybatisdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "userService")
public class UserServiceImpl implements UserService{

    @Autowired
    UserMapper userMapper;

    @Override
    public int insertUser(User user) {
        return userMapper.insertSelective(user);
    }

    @Override
    public List<User> findAllUser(int pageNum, int pageSize) {

        PageHelper.startPage(pageNum, pageSize);
        return userMapper.selectAllUser();
    }
}
