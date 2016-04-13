package com.mum.scrum.service;

import com.mum.scrum.model.UserStory;

import java.util.List;

/**
 * Created by 984609 on 4/12/2016.
 */
public interface UserStoryService {
    List<String> validateUserStoryCreation(UserStory userStory);
    List<String> validateUserStoryUpdate(UserStory userStory);
    void persistUserStory(UserStory userStory);
    void updateUserStory(long userStoryId, UserStory userStory);
}
