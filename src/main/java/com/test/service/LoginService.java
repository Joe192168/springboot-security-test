package com.test.service;

import com.test.domain.entity.User;
import com.test.domain.pojo.MsgResult;

public interface LoginService {

    MsgResult login(User user);

}
