package com.test.mapper;

import com.test.domain.entity.Authority;
import com.test.domain.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    User getUserByUsername(String username);

    List<Authority> getRolesByUsername(String username);

}
