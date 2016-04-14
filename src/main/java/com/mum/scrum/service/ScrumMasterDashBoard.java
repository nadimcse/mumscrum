package com.mum.scrum.service;

import com.mum.scrum.dao.ProjectDao;
import com.mum.scrum.model.User;
import com.mum.scrum.viewmodel.Login;
import com.mum.scrum.viewmodel.PermissionModel;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 984609 on 4/14/2016.
 */
public class ScrumMasterDashBoard implements DashBoard {

    @Autowired
    private TokenGeneratorService tokenGeneratorService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProjectService projectService;

    @Override
    public Map<String, Object> populateData(Login login) {
        Map<String, Object> map = new HashMap<>();

        User user = userService.getUser(login.getEmail());

        map.put("projectList", projectService.getProjectsByScrumMaster(user.getId()));
        map.put("token", tokenGeneratorService.generateToken(user));
        map.put("individual", user);
        map.put("permission", PermissionModel.getScrumMasterPermission());

        return map;
    }
}
