package com.dwj.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dwj.common.result.JsonResult;
import com.dwj.common.result.ResultCode;
import com.dwj.common.result.ResultTool;
import com.dwj.mapper.UserMapper;
import com.dwj.pojo.User;
import com.dwj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserMapper userMapper;

    /**
     * 登录
     *
     * @param user
     * @return
     */
    @Override
    public JsonResult login(User user) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username",user.getUsername());
        User user1 = userMapper.selectOne(wrapper);
        if (user1 != null) {
            //查询结果不为null
            if (user1.getPassword().equals(user.getPassword())) {
                //密码相同登录成功(进行JWT加密)
                Calendar instance = Calendar.getInstance();
                //登录有效时间为6个小时，超过6个小时之后需要重新登录
                instance.add(Calendar.SECOND,21600);
                String token = JWT.create().withClaim("username", user1.getUsername()).withExpiresAt(instance.getTime()).sign(Algorithm.HMAC256("@#qwer"));
                Map map = new HashMap<String,String>();
                map.put("token",token);
                map.put("username",user1.getUsername());
                return ResultTool.success(map);
            } else {
                //密码不同登录失败
                return ResultTool.fail(ResultCode.USER_CREDENTIALS_ERROR);
            }
        }
        //不存在该用户名的用户
        return ResultTool.fail(ResultCode.USER_ACCOUNT_NOT_EXIST);
    }
}
