package com.mum.scrum.service;

import com.mum.scrum.model.UserStory;

import java.util.Map;

/**
 * Created by Razib Mollick on 4/11/2016.
 */
@Service("userStoryService")
public class UserStoryServiceImp implements UserStoryService {

    @Autowired
    Environment environment;

    @Autowired
    private UserStoryDao userStoryDao;


    @Override
    public UserStory getUser(long usrId) {
        return null;
    }

    @Override
    public void persistUser(UserStory user) {

    }

    @Override
    public void deleteUser(UserStory user) {

    }

    @Override
    public void validateUser(UserStory user) {

    }

    @Override
    public Map<String, Object> handleGetUser(long userId) {
        return null;
    }

    @Override
    public Map<String, Object> validateUsrCreation(UserStory user) {
        return null;
    }

    @Override
    public void updateUser(long userId, UserStory user) {

    }
}
