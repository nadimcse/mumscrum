package com.mum.scrum.service;

import com.mum.scrum.model.UserStory;

import java.util.Map;

/**
 * Created by Razib Mollick on 4/10/2016.
 */
public interface UserStoryService {

    UserStory getUser(long usrId);
    void persistUser(UserStory user);
    void deleteUser(UserStory user);
    void validateUser(UserStory user);
    Map<String, Object> handleGetUser(long userId);
    Map<String, Object> validateUsrCreation(UserStory user);
    void updateUser(long userId, UserStory user);


}
