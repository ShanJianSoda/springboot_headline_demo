package com.chen.service;

import com.chen.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.chen.utils.Result;

/**
* @author chenchen
* @description 针对表【news_user】的数据库操作Service
* @createDate 2023-10-09 14:26:11
*/
public interface UserService extends IService<User> {

    Result login(User user);

    Result getUserInfo(String token);

    Result checkUserName(String username);

    Result regist(User user);

    Result checkLogin(String token);
}
