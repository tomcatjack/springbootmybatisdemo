package com.wei.springbootmybatisdemo.controller;

import com.wei.springbootmybatisdemo.model.User;
import com.wei.springbootmybatisdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller
public class UserController {

    @Autowired
    UserService userService;

    @ResponseBody
    @RequestMapping("/addUser")
    public int add(User user) throws Exception {
        user.setUserId(2134);
        user.setPassword("123");
        user.setPhone("23");
        user.setUserName("luqi12");
        return userService.insertUser(user);
    }

    @ResponseBody
    @RequestMapping(value = "/all/{pageNum}/{pageSize}")
    public Object findAllUser(@PathVariable("pageNum") int pageNum, @PathVariable("pageSize") int pageSize){

        return userService.findAllUser(pageNum,pageSize);
    }

    @ResponseBody
    @RequestMapping(value = "/get/{id}")
    public User getByKey(@PathVariable("id") Integer id){
        return userService.getBykeys(id);
    }

    @ResponseBody
    @RequestMapping(value = "/get")
    public Object getAll() throws Exception {
        List<User> all = userService.getAll();
        return all;
    }

}
