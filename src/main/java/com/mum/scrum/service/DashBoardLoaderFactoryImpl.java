package com.mum.scrum.service;

import com.mum.scrum.dao.UserDao;
import com.mum.scrum.model.Role;
import com.mum.scrum.model.User;
import com.mum.scrum.viewmodel.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: nadim
 * Date: 4/8/16
 * Time: 11:10 PM
 * To change this template use File | Settings | File Templates.
 */
@Service("dashBoardLoaderFactory")
public class DashBoardLoaderFactoryImpl extends DashBoardLoaderFactory {

    @Autowired
    UserDao userDao;

    @Autowired
    @Qualifier("systemAdminDashBoard")
    DashBoard systemAdminDashBoard;

    @Autowired
    @Qualifier("productOwnerDashBoard")
    DashBoard productOwnerDashBoard;

    @Override
    DashBoard resolvedDashboard(Login login) {

        User user = userDao.getUser(login.getEmail());


        Role role = user.getRole();
        if (1 == role.getId()) {
            return systemAdminDashBoard;
        } else if (2 == role.getId()) {
            return productOwnerDashBoard;
        }

        return null;
    }
}
