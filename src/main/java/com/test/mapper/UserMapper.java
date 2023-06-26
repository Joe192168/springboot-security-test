package com.test.mapper;

import com.test.domain.entity.TRole;
import com.test.domain.entity.TUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    TUser getUserByUsername(String username);

    List<TRole> getRolesByUsername(String username);

}
