package com.mum.scrum.dao;

import com.mum.scrum.model.Sprint;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

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

    @Override
    public List<Sprint> getAllSprints(long projectId) {
        Query query = em.createQuery("SELECT s FROM Sprint s where s.project.id = :projectId")
                .setParameter("projectId", projectId);
        return  (List<Sprint>)query.getResultList();
    }
}
