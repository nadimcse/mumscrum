package com.mum.scrum.dao;

import com.mum.scrum.model.UserStory;

import java.util.List;

/**
 * Created by Razib Mollick on 4/11/2016.
 */
@Repository
@Transactional
public class UserStoryDaoImp implements UserStoryDao{

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<UserStory> getAllUsersStory() {
        return null;
    }

    @Override
    public void persistUserStory(UserStory user) {

    }

    @Override
    public void deleteUserStroy(UserStory user) {

    }
}
