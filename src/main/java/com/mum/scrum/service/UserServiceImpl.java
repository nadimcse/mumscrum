package com.mum.scrum.service;

import com.mum.scrum.dao.UserDao;
import com.mum.scrum.utility.Utility;
import com.mum.scrum.viewmodel.Login;
import com.mum.scrum.model.User;
import com.mum.scrum.viewmodel.PermissionModel;
import com.mum.scrum.viewmodel.ViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

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
    Environment environment;

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


    public Map<String, Object> validateUsrCreation(User user) {

        Map<String, Object> map = new HashMap<>();

        ///check permission
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String token = request.getParameter("token");

        if (!StringUtils.isEmpty(token)) {
            String[] split = token.split("\\|");
            if (!Utility.hasPermission("canCreateUser", Integer.valueOf(split[1]))) {
                map.put("message", "Unauthorized access!!!");
                return map;
            }
        }

        //is user already exist
        User existedUser = userDao.getUser(user.getEmail());
        if (existedUser == null) {
            map.put("message", "User already exists!!!");
            return map;
        }
        return map;   //TODO return better way to validation error
    }
}
