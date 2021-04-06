package com.dwj.service;

import com.dwj.common.result.JsonResult;
import com.dwj.pojo.Blog;

public interface BlogService {
    JsonResult save(Blog blog);

    JsonResult findAll();

    JsonResult findById(Integer id);
}
