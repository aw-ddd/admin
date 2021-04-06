package com.dwj.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Blog {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    private String title;
    private String text;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
