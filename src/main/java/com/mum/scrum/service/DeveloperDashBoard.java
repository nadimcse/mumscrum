package com.mum.scrum.service;

import com.mum.scrum.model.User;
import com.mum.scrum.viewmodel.Login;
import com.mum.scrum.viewmodel.PermissionModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 984609 on 4/18/2016.
 */
@Service("developerDashBoard")
public class DeveloperDashBoard implements DashBoard {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private UserService userService;

    @Autowired
    private TokenGeneratorService tokenGeneratorService;


    @Override
    public Map<String, Object> populateData(Login login) {
        Map<String, Object> dataMap = new HashMap<>();
        User user = userService.getUser(login.getEmail());
        dataMap.put("projectList", projectService.getProjectsByDeveloper(user.getId()));
        dataMap.put("token", tokenGeneratorService.generateToken(user));
        dataMap.put("individual", user);
        dataMap.put("permission", PermissionModel.getDeveloperPermission());
        return dataMap;
    }
}
