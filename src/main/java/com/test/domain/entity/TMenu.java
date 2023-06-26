package com.test.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 菜单实体类
 */
@Data
public class TMenu {

    private Integer id;
    private String name;
    private Integer type;
    private String url;
    private Integer parentId;
    private String parentIds;
    private String permission;
    private String menuIcon;
    private String remarks;
    private String createBy;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;
    private String updateBy;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateDate;
    private Integer delFlag;

    List<TRole> roles;

}
