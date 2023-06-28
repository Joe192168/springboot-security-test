package com.test.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户表实体
 */
@Data
public class TUser implements Serializable {

    private static final long serialVersionUID = 6611366543246062194L;
    private Integer id;
    private String username;
    private String password;
    private Integer sex;
    private String email;
    private String mobile;
    private Integer status;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    public boolean isAdmin() {
        return isAdmin(this.id);
    }

    public static boolean isAdmin(Integer userId) {
        return userId != null && 1L == userId;
    }
}
