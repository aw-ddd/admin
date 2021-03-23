package com.dwj.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.dwj.common.result.JsonResult;
import com.dwj.common.result.ResultCode;
import com.dwj.common.result.ResultTool;
import com.dwj.mapper.UserMapper;
import com.dwj.pojo.User;
import com.dwj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Calendar;

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
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("username", user.getUsername());
        User user1 = userMapper.selectOneByExample(example);
        if (user1 != null) {
            //查询结果不为null
            if (user1.getPassword().equals(user.getPassword())) {
                //密码相同登录成功(进行JWT加密)
                Calendar instance = Calendar.getInstance();
//                instance.add(Calendar.SECOND,21600);
                instance.add(Calendar.SECOND,30);
                String token = JWT.create().withClaim("username", user1.getUsername()).withExpiresAt(instance.getTime()).sign(Algorithm.HMAC256("@#qwer"));
                return ResultTool.success(token);
            } else {
                //密码不同登录失败
                return ResultTool.fail(ResultCode.USER_CREDENTIALS_ERROR);
            }
        }
        //不存在该用户名的用户
        return ResultTool.fail(ResultCode.USER_ACCOUNT_NOT_EXIST);
    }
}
