package com.mum.scrum.service;

import com.mum.scrum.dao.SprintDao;
import com.mum.scrum.model.Project;
import com.mum.scrum.model.Sprint;
import com.mum.scrum.utility.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by 984609 on 4/12/2016.
 */
@Service("sprintService")
@Transactional
public class SprintServiceImpl implements SprintService {

    @Autowired
    private SprintDao sprintDao;

    @Override
    public void persist(Sprint sprint) {
        sprintDao.persist(sprint);
    }

    @Override
    public List<String> validateSprintCreation(Sprint sprint) {
        return validatePermission("canCreateSprint");
    }

    @Override
    public List<String> validateSprintUpdate(Sprint sprint) {
        return validatePermission("canUpdateSprint");
    }

    private List<String> validatePermission(String permission) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String token = request.getParameter("token");

        if (!StringUtils.isEmpty(token)) {
            String[] split = token.split("\\|");
            if (!Utility.hasPermission(permission, Integer.valueOf(split[1]))) {
                return Arrays.asList("Unauthorized access!!!");
            }
        }
        return Arrays.asList();

    }

    @Override
    public void updateSprint(long sprintId, Sprint sprint) {
        Sprint sprintObj = sprintDao.getSprint(sprintId);
        sprintObj.setName(sprint.getName());

        if (sprint.getProject() != null) {
            sprintObj.setProject(new Project(sprint.getProject().getId()));
        }
        sprintDao.persist(sprintObj);

    }
}
