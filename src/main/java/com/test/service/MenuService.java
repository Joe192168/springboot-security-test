package com.test.service;

import com.test.domain.entity.TMenu;

import java.util.List;
import java.util.Set;

public interface MenuService {

    Set<String> selectMenuPermsByUserId(Integer userId);

}
