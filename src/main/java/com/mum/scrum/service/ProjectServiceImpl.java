package com.mum.scrum.service;

import com.mum.scrum.dao.ProjectDao;
import com.mum.scrum.model.Project;
import com.mum.scrum.model.Sprint;
import com.mum.scrum.model.User;
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
@Service("projectService")
@Transactional
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    ProjectDao projectDao;

    @Override
    public void persist(Project project) {
        projectDao.persistProject(project);
    }

    @Override
    public void updateProject(long projectId, Project project) {
        Project projectObj = projectDao.getProject(projectId);

        projectObj.setName(project.getName());
        if (project.getManagedBy() != null) {
            projectObj.setManagedBy(new User(project.getManagedBy().getId()));
        }

        if (project.getOwner() != null) {
            projectObj.setOwner(new User(project.getOwner().getId()));
        }

/*
        if (project.getSprints() != null) {
            List<Sprint> sprints = new ArrayList<>();

            for (Sprint sprint : project.getSprints()) {
                sprints.add(new Sprint(sprint.getId()));
            }
            projectObj.setSprints(sprints);
        }
*/

        projectDao.persistProject(projectObj);

    }

    @Override
    public List<String> validateProjectCreation(Project project) {

        return validatePermission("canCreateProject");
    }

    @Override
    public List<String> validateProjectUpdate(Project project) {
       return validatePermission("canUpdateProject");
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
}