package com.dwj.service;

import com.dwj.common.result.JsonResult;
import com.dwj.pojo.User;

public interface UserService {
    JsonResult login(User user);
}
