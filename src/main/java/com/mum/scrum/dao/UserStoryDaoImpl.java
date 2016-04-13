package com.mum.scrum.dao;

import com.mum.scrum.model.UserStory;
import com.mum.scrum.service.UserStoryService;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by 984609 on 4/12/2016.
 */
@Repository
public class UserStoryDaoImpl implements UserStoryDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void persistUserStory(UserStory userStory) {
        em.persist(userStory);
    }

    @Override
    public UserStory getUserStory(long userStoryId) {
        return em.find(UserStory.class, userStoryId);
    }
}
