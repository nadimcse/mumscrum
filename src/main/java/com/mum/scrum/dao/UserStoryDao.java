package com.mum.scrum.dao;

import com.mum.scrum.model.UserStory;

import java.util.List;

/**
 * Created by Razib Mollick on 4/11/2016.
 */
public interface UserStoryDao {

    List<UserStory> getAllUsersStory();
    void persistUserStory(UserStory user);
    void deleteUserStroy(UserStory user);
}
