package com.mum.scrum.service;

import com.mum.scrum.dao.UserDao;
import com.mum.scrum.model.User;
import com.mum.scrum.viewmodel.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 984609 on 4/8/2016.
 */
@Service("loginService")
public class LoginServiceImpl implements LoginService {

    @Autowired
    UserDao userDao;

    @Override
    public boolean authenticateLogin(Login login) {
        User user = userDao.getUser(login.getEmail());
        if (user == null) {
            return false; //user not found
        }
        if (user.getPassword().equals(login.getPassword())) {
            return true;
        }
        return false;
    }
}
