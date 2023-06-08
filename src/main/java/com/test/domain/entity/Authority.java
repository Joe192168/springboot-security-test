package com.test.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 权限表实体
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Authority implements Serializable {

    private static final long serialVersionUID = 7456980055276242684L;
    private int id;
    private String userName;
    private String authority;

}
