package com.mum.scrum.service;

import com.mum.scrum.dao.SprintDao;
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
import java.util.*;

/**
 * Created by 984609 on 4/12/2016.
 */
@Service("userStoryService")
@Transactional
public class UserStoryServiceImpl implements UserStoryService {

    @Autowired
    private UserStoryDao userStoryDao;

    @Autowired
    private SprintDao sprintDao;

    @Override
    public void persist(UserStory userStory) {
        userStoryDao.persist(userStory);

        if (userStory.getSprints() != null) {

            for (Sprint sprintObj : userStory.getSprints()) {
                Sprint sprintPersistObj = sprintDao.getSprint(sprintObj.getId());
                if (sprintPersistObj.getUserStories() == null) {
                    sprintPersistObj.setUserStories(new ArrayList<UserStory>());
                }

                sprintPersistObj.getUserStories().add(userStory);
                sprintDao.persist(sprintPersistObj);

            }
        }
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
    public List<String> validateUserStoryLoad(long userStoryId) {
        return validatePermission("canViewUserStory");
    }

    @Override
    public Map<String, Object> handleGetUserStory(long userstoryId) {
        Map<String, Object> map = new HashMap<>();
        UserStory userStory = userStoryDao.getUserStory(userstoryId);
        map.put("userStoryList", Arrays.asList(userStory));
      //  map.put("sprintList", userStory.getSprints());
      //  map.put("logTimeList", userStory.getLogTimes());
        return map;
    }

    @Override
    public void updateUserStory(long userStoryId, UserStory userStory) {
        UserStory userStoryObj = userStoryDao.getUserStory(userStoryId);
        userStoryObj.setTitle(userStory.getTitle());
        userStoryObj.setDescription(userStory.getDescription());
        userStoryObj.setEstimation(userStory.getEstimation());
        if (userStory.getProject() != null) {
            userStoryObj.setProject(new Project(userStory.getProject().getId()));
        }

        userStoryDao.persist(userStoryObj);

        if (userStory.getSprints() != null) { //TODO save everything from one command

            for (Sprint sprintObj : userStory.getSprints()) {
                Sprint sprintPersistObj = sprintDao.getSprint(sprintObj.getId());
                if (sprintPersistObj.getUserStories() == null) {
                    sprintPersistObj.setUserStories(new ArrayList<UserStory>());
                }

                sprintPersistObj.getUserStories().add(userStoryObj);
                sprintDao.persist(sprintPersistObj);

            }
        }


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
