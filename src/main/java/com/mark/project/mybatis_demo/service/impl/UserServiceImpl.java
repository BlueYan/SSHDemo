package com.mark.project.mybatis_demo.service.impl;

import com.mark.project.mybatis_demo.domain.User;
import com.mark.project.mybatis_demo.mapper.UserMapper;
import com.mark.project.mybatis_demo.service.IUserService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Mark on 17/8/26.
 */
@Service
public class UserServiceImpl implements IUserService {

    //这里的dao我们是使用了mapper接口来作为dao
    @Autowired
    private UserMapper userDAO;

    public void save(User user) {
        userDAO.save(user);
    }
}
