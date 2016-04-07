package com.mum.scrum.viewmodel;

/**
 * Created with IntelliJ IDEA.
 * User: nadim
 * Date: 4/6/16
 * Time: 5:53 PM
 * To change this template use File | Settings | File Templates.
 */
public class DeveloperPermissionModel implements PermissionModel {
    @Override
    public boolean viewUser() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean createUser() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean updateUser() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean deleteUser() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean viewSprint() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean createSprint() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean updateSprint() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean deleteSprint() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
