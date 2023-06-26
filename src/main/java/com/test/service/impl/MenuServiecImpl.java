package com.test.service.impl;

import com.test.mapper.MenuMapper;
import com.test.service.MenuService;
import com.test.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @ClassName: 菜单业务层
 * @Author : xqh
 * @Date :2023/6/8
 * @Description: TODO
 */
@Service
public class MenuServiecImpl implements MenuService {

    @Autowired
    public MenuMapper menuMapper;

    @Override
    public Set<String> selectMenuPermsByUserId(Integer userId) {
        List<String> perms = menuMapper.selectMenuPermsByUserId(userId);
        Set<String> permsSet = new HashSet<>();
        for (String perm : perms) {
            if (StringUtils.isNotEmpty(perm)) {
                permsSet.addAll(Arrays.asList(perm.trim().split(",")));
            }
        }
        return permsSet;
    }
}
