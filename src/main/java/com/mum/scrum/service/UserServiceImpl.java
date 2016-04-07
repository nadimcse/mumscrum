package com.mum.scrum.service;

import com.mum.scrum.dao.UserDao;
import com.mum.scrum.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: nadim
 * Date: 4/6/16
 * Time: 7:59 PM
 * To change this template use File | Settings | File Templates.
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User getUser(long usrId) {
        return userDao.getUser(usrId);
    }


    @Override
    public void persistUser(User user) {
        userDao.persistUser(user);
    }

    @Override
    public void deleteUser(User user) {
        userDao.deleteUser(user);
    }

    @Override
    public void validateUser(User user) {
        ///TODO
    }
}
