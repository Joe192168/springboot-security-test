package com.test.domain.entity;

import lombok.Data;

/**
 * 用户角色关系表
 */
@Data
public class TUserRole {

    private Integer id;
    private Integer userId;
    private Integer roleId;

}
