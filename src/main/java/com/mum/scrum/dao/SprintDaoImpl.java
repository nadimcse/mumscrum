package com.mum.scrum.dao;

import com.mum.scrum.model.Sprint;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by 984609 on 4/12/2016.
 */
@Repository
public class SprintDaoImpl implements SprintDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void persist(Sprint sprint) {
        em.persist(sprint);
    }

    @Override
    public Sprint getSprint(long sprintId) {
        return em.find(Sprint.class, sprintId);
    }
}
