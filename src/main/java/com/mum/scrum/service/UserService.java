package com.mum.scrum.service;

import com.mum.scrum.viewmodel.Login;
import com.mum.scrum.model.User;
import com.mum.scrum.viewmodel.ViewModel;

/**
 * Created with IntelliJ IDEA.
 * User: nadim
 * Date: 4/6/16
 * Time: 7:57 PM
 * To change this template use File | Settings | File Templates.
 */
public interface UserService {

    User getUser(long usrId);
    void persistUser(User user);
    void deleteUser(User user);
    void validateUser(User user);
    ViewModel handleGetUser(long userId);
    boolean doUserValidation(User user);



}
