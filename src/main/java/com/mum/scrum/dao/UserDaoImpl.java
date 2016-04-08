package com.mum.scrum.dao;

import com.mum.scrum.model.Sprint;
import com.mum.scrum.model.User;
import com.mum.scrum.model.UserStory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: nadim
 * Date: 4/5/16
 * Time: 11:14 PM
 * To change this template use File | Settings | File Templates.
 */

@Repository
@Transactional
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void saveJpaContact() {


//        User user = new User();
//        user.setEmail("ff");
//        user.setFirstName("ff1");
//        em.persist(user);
//
//        Project project = new Project();
//        project.setName("11");
//        project.setStartDate(new Date());
//        project.setEndDate(new Date());
//
//        List<User> users = new ArrayList<>();
//        users.add(user);
//        project.setUsers(users);
//
//        em.persist(project);
//        System.out.println(".......");

        UserStory userStory1 = new UserStory();
        userStory1.setTitle("cc");
        userStory1.setDescription("cc1");
        // em.persist(userStory1);

        Sprint sprint = new Sprint();
        sprint.setName("dd");
        //  em.persist(sprint);

        userStory1.getSprints().add(sprint);
        sprint.getUserStories().add(userStory1);

//        List<UserStory> userStories = new ArrayList<>();
//        userStories.add(userStory1);
//        sprint.setUserStories(userStories);


        em.persist(sprint);


//        sprint = em.find(Sprint.class, sprint.getId());
//        System.out.println(sprint.getUserStories().size() + "...........");
                                                                                   //not workink in rewverse side
//        List<Sprint> sprints = new ArrayList<>();
//        sprints.add(sprint);
//        userStory1.setSprints(sprints);
//        em.persist(userStory1);
//
//
        sprint = em.find(Sprint.class, sprint.getId());
        System.out.println(sprint.getUserStories().size() + "...........");


    }

    @Override
    public User getUser(long usrId) {
        return em.find(User.class, usrId);
    }

    @Override
    public User getUser(String email) {
        Query query = em.createQuery("SELECT u FROM User u where u.email = :email")
                .setParameter("email", email);
        List<User> resultList =  query.getResultList();
        if (resultList.isEmpty()) {
            return null;
        }
        return resultList.get(0);
    }

    @Override
    public List<User> getAllUsers() {
        Query query = em.createQuery("SELECT u FROM User u");
        return  query.getResultList();
    }

    @Override
    public void persistUser(User user) {
        em.persist(user);
    }

    @Override
    public void deleteUser(User user) { //TODO more works needed
        em.remove(user);
    }
}
