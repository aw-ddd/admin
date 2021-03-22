package com.dwj.pojo;

import lombok.Data;

import javax.persistence.Table;

@Data
@Table(name = "user")
public class User {
    private Integer id;
    private String username;
    private String password;
}
