package com.test.domain.entity;

import lombok.Data;

/**
 * 角色-菜单关系表
 */
@Data
public class TRoleMenu {

    private Integer id;
    private Integer roleId;
    private Integer menuId;
}
