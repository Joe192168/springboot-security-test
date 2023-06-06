package com.test.domain.entity;

import lombok.Data;

import java.util.List;

/**
 * @Author : xqh
 * @Date :2023/5/31
 * @Description: TODO
 */
@Data
public class User {

    private Integer id;
    private String username;
    private String password;

    private List<Authority> authorityList;
}
