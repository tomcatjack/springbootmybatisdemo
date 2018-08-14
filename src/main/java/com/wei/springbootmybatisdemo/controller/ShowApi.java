package com.wei.springbootmybatisdemo.controller;

import lombok.extern.slf4j.Slf4j;
import net.rubyeye.xmemcached.MemcachedClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author LUCCI
 * @date 2018/8/3 23:08
 * @Description:
 * @Modify:
 */
@Slf4j
@Service
public class ShowApi {

    @Autowired
    MemcachedClient memcachedClient;

    //新增
    public void add(String k, String v) {
        try {
            memcachedClient.set(k, 0, v);
        } catch (Exception e) {
            log.info("新增失败！", e);
        }
    }

    //获取
    public String quary(String k) {
        try {
            String v = memcachedClient.get(k);
            return v;
        } catch (Exception e) {
            log.info("获取失败！", e);
        }
        return null;
    }

}
