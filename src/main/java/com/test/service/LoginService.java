package com.test.service;

import com.test.domain.entity.TUser;
import com.test.domain.pojo.MsgResult;

public interface LoginService {

    MsgResult login(TUser TUser);

    MsgResult logout();

}
