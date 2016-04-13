package com.mum.scrum.dao;

import com.mum.scrum.model.Project;
import com.mum.scrum.model.Sprint;
import com.mum.scrum.model.UserStory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: nadim
 * Date: 4/6/16
 * Time: 5:00 PM
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class ProjectDaoImpl implements ProjectDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void persistProject(Project project) {
        em.persist(project);
    }

    @Override
    public Project getProject(long projectId) {
        return em.find(Project.class, projectId);
    }



    @Override
    public void deleteProject(Project project) {
        em.remove(project);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Project> getAllProjects() {
        Query query = em.createQuery("SELECT p FROM Project p");
        return  (List<Project>) query.getResultList();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Project> getProjects(long userId) {
        Query query = em.createQuery("SELECT p FROM Project p where p.owner.id = :ownerId")
                .setParameter("ownerId", userId);
        List<Project> projects =   (List<Project>) query.getResultList();
        return projects;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Sprint> getAllSprints(long projectId) {
        Query query = em.createQuery("SELECT s FROM Sprint s where s.id = :projectId")
                .setParameter("projectId", projectId);
        return  (List<Sprint>)query.getResultList();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<UserStory> getProductsBackLog() {
        Query query = em.createQuery("SELECT u FROM UserStory u");    ///TODO need to check status
        return  (List<UserStory>)query.getResultList();
    }

    @Override
    public List<Project> getUserProjects(long userId) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<UserStory> getAllUserStories(long sprintId) {
        Query query = em.createQuery("SELECT u FROM UserStory u where u.id = :sprintId")
                .setParameter("sprintId", sprintId);
        return  (List<UserStory>)query.getResultList();
    }
}
