package com.test.mapper;

import com.test.domain.entity.TMenu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MenuMapper {

    List<String> selectMenuPermsByUserId(Integer userId);

}
