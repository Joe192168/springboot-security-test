package com.test.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.util.Date;

/**
 * 角色实体类
 */
@Data
public class TRole {

    private Integer id;
    private String roleName;
    private String roleRemark;
    private Integer userIdCreate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

}
