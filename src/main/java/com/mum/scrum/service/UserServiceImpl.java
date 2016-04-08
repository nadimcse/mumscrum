package com.mum.scrum.service;

import com.mum.scrum.dao.UserDao;
import com.mum.scrum.viewmodel.Login;
import com.mum.scrum.model.User;
import com.mum.scrum.viewmodel.PermissionModel;
import com.mum.scrum.viewmodel.ViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;

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

    @Override
    public ViewModel handleGetUser(long userId) {
        ///validate user
        User user = getUser(userId);
        ViewModel viewModel = new ViewModel();
        viewModel.getDataMap().put("user", user);
        viewModel.getDataMap().put("permission", PermissionModel.getProductOwnerPermission());//TODO add role to return permission
        return viewModel;
    }


    @Override
    public boolean doUserValidation(User user) {

        if (StringUtils.isEmpty(user.getPassword())) {
            return false;
        }
        User existedUser = userDao.getUser(user.getEmail());
        if (existedUser == null) {
            return true;
        }
        return false;
    }
}
