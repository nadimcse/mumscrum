package com.mum.scrum.service;

import com.mum.scrum.dao.UserDao;
import com.mum.scrum.viewmodel.Login;
import com.mum.scrum.viewmodel.PermissionModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: nadim
 * Date: 4/8/16
 * Time: 9:38 PM
 * To change this template use File | Settings | File Templates.
 */
@Service("systemAdminDashBoard")
public class SystemAdminDashBoard implements DashBoard {

    @Autowired
    Environment environment;

    @Autowired
    private UserDao userDao;

    @Autowired
    private TokenGeneratorService tokenGeneratorService;

    @Override
    public Map<String, Object> populateData(Login login) {
        Map<String, Object> map = new HashMap<>();

        map.put("userList", userDao.getAllUsers());
        map.put("token", tokenGeneratorService.generateToken(userDao.getUser(login.getEmail())));
        map.put("permission", PermissionModel.getSystemAdminPermission());

        return map;
    }


}