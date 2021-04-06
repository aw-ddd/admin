package com.dwj.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dwj.common.exception.CustomException;
import com.dwj.common.result.JsonResult;
import com.dwj.common.result.ResultTool;
import com.dwj.mapper.BlogMapper;
import com.dwj.pojo.Blog;
import com.dwj.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogMapper blogMapper;

    @Override
    public JsonResult save(Blog blog) {
        if (null == blog.getId()){
            //新增
            blog.setCreateTime(LocalDateTime.now());
             blogMapper.insert(blog);

        }else {
            //编辑
            QueryWrapper<Blog> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("id",blog.getId());
            blog.setUpdateTime(LocalDateTime.now());
            int i = blogMapper.update(blog,queryWrapper);
            if (i <= 0){
                //修改失败
                throw new CustomException(999);
            }
        }
        return ResultTool.success(blog);
    }

    @Override
    public JsonResult findAll() {
        List<Blog> blogs = blogMapper.selectList(null);
        return ResultTool.success(blogs);
    }

    @Override
    public JsonResult findById(Integer id) {
        QueryWrapper<Blog> wrapper = new QueryWrapper<>();
       wrapper.eq("id",id);
        Blog blog = blogMapper.selectOne(wrapper);
        return ResultTool.success(blog);
    }
}
