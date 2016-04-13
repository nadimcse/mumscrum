package com.mum.scrum.service;

import com.mum.scrum.dao.UserStoryDao;
import com.mum.scrum.model.Project;
import com.mum.scrum.model.Sprint;
import com.mum.scrum.model.UserStory;
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
@Service("userStory")
@Transactional
public class UserStoryServiceImpl implements UserStoryService {

    @Autowired
    private UserStoryDao userStoryDao;

    @Override
    public void persistUserStory(UserStory userStory) {
        userStoryDao.persistUserStory(userStory);
    }

    @Override
    public List<String> validateUserStoryCreation(UserStory userStory) {
        return validatePermission("canCreateUserStory");
    }

    @Override
    public List<String> validateUserStoryUpdate(UserStory userStory) {
        return validatePermission("canUpdateUserStory");
    }

    @Override
    public void updateUserStory(long userStoryId, UserStory userStory) {
        UserStory userStoryObj = userStoryDao.getUserStory(userStoryId);
        userStory.setTitle(userStory.getTitle());
        userStory.setDescription(userStory.getDescription());
        userStory.setEstimation(userStory.getEstimation());
        if (userStory.getProject() != null) {
            userStory.setProject(new Project(userStory.getProject().getId()));
        }

        if (userStory.getSprints() != null) {

            List<Sprint> sprints = new ArrayList<>();
            for (Sprint sprint : userStory.getSprints()) {
                sprints.add(new Sprint(sprint.getId()));
            }
            userStoryObj.setSprints(sprints);
        }
        userStoryDao.persistUserStory(userStory);

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
