package com.mum.scrum.viewmodel;

/**
 * Created with IntelliJ IDEA.
 * User: nadim
 * Date: 4/6/16
 * Time: 5:52 PM
 * To change this template use File | Settings | File Templates.
 */
public class SysAdminPermissionModel implements PermissionModel {
    @Override
    public boolean viewUser() {
        return false;
    }

    @Override
    public boolean createUser() {
        return true;
    }

    @Override
    public boolean updateUser() {
        return true;
    }

    @Override
    public boolean deleteUser() {
        return true;
    }

    @Override
    public boolean viewSprint() {
        return false;
    }

    @Override
    public boolean createSprint() {
        return false;
    }

    @Override
    public boolean updateSprint() {
        return false;
    }

    @Override
    public boolean deleteSprint() {
        return false;
    }
}
