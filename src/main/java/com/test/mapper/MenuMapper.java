package com.test.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MenuMapper {

    List<String> selectMenuPermsByUserId(Integer userId);

}
