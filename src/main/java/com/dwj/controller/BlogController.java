package com.dwj.controller;

import com.dwj.common.result.JsonResult;
import com.dwj.pojo.Blog;
import com.dwj.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("blog")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @PostMapping("save")
    public JsonResult save(@RequestBody Blog blog) {
        JsonResult result = blogService.save(blog);
        return result;
    }

    @GetMapping("findAll")
    public JsonResult findAll() {
        JsonResult result = blogService.findAll();
        return result;
    }

    @GetMapping("/findById/{id}")
    public JsonResult findById(@PathVariable Integer id){
        JsonResult result = blogService.findById(id);
        return result;
    }
}
