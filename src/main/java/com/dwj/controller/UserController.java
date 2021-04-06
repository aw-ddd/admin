package com.dwj.controller;

import com.dwj.common.result.JsonResult;
import com.dwj.common.result.ResultTool;
import com.dwj.pojo.User;
import com.dwj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 登录
     * @param user
     * @return
     */
    @PostMapping("login")
    public JsonResult login(@RequestBody User user) {
        JsonResult jsonResult = userService.login(user);
        return jsonResult;
    }
}
