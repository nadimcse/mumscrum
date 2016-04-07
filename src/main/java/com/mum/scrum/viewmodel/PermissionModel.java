package com.mum.scrum.viewmodel;

/**
 * Created with IntelliJ IDEA.
 * User: nadim
 * Date: 4/6/16
 * Time: 5:22 PM
 * To change this template use File | Settings | File Templates.
 */
public interface PermissionModel {

    boolean viewUser();
    boolean createUser();
    boolean updateUser();
    boolean deleteUser();

    boolean viewSprint();
    boolean createSprint();
    boolean updateSprint();
    boolean deleteSprint();


}
