package com.wei.springbootmybatisdemo.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.classmate.types.ResolvedRecursiveType;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wei.springbootmybatisdemo.mapper.UserMapper;
import com.wei.springbootmybatisdemo.model.User;
import com.wei.springbootmybatisdemo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import net.rubyeye.xmemcached.MemcachedClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@Slf4j
@Service(value = "userService")
public class UserServiceImpl implements UserService {


    private static final String INDEX_NAME = "indexUuids";
    @Autowired
    UserMapper userMapper;
    @Autowired
    MemcachedClient memcachedClient;

    @Override
    public int insertUser(User user) throws Exception {
        int i = userMapper.insertSelective(user);
        if (i > 0) {
            String json = JSON.toJSONString(user);
            String uuids = memcachedClient.get(INDEX_NAME);
            if (uuids != null && uuids.trim().length() > 0) {
                // 如果存在，修改indexUuids
                memcachedClient.append(INDEX_NAME, "," + user.getUserId());
                memcachedClient.set("" + user.getUserId(), 0, user);
            } else {
                // 如果不存在， 初始化一个indexUuids -> tblhouse.uuid
                memcachedClient.set(INDEX_NAME, 0, "" + user.getUserId());
                memcachedClient.set("" + user.getUserId(), 0, user);
            }
        } else {

        }
        return i;
    }

    @Override
    public List<User> findAllUser(int pageNum, int pageSize) {

        PageHelper.startPage(pageNum, pageSize);
        return userMapper.selectAllUser();
    }

    @Override
    public User getBykeys(Integer id) {
        User user = null;
        try {
            String uuids = memcachedClient.get(INDEX_NAME);
            if (uuids != null && uuids.trim().length() > 0) {
                List<String> list = Arrays.asList(uuids.split(","));
                if (list.contains(String.valueOf(id))) {
                    user = memcachedClient.get(String.valueOf(id));
                    return user;
                }
            }
            user = userMapper.selectByPrimaryKey(id);
            return user;
        } catch (Exception e) {
            log.info("getBykeys异常！",e);
        }
        return null;
    }

    @Override
    public List<User> getAll() throws Exception {
        List<String> keys = new ArrayList<>();
        List<User> users = new ArrayList<>();

        String uuids = memcachedClient.get(INDEX_NAME);
        // 组织所有key集合
        keys = Arrays.asList(uuids.split(","));
        Map<String, User> maps = memcachedClient.get(keys);
        for (User userJson : maps.values()) {
            //User user = JSON.parseObject(userJson,User.class);
            users.add(userJson);
        }

        return users;
    }
}
