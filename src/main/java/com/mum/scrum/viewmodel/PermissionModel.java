package com.mum.scrum.viewmodel;

/**
 * Created with IntelliJ IDEA.
 * User: nadim
 * Date: 4/6/16
 * Time: 5:22 PM
 * To change this template use File | Settings | File Templates.
 */
public class PermissionModel {

    private static String[] SYS_ADMIN_PERMISSION = new String[] {"canViewUser", "canCreateUser", "canDeleteUser", "canUpdateUser"};
    private static String[] PRODUCT_OWNER_PERMISSION = new String[] {"canViewProject", "canCreateProject", "canDeleteProject", "canUpdateProject"};

    public static String[] getSystemAdminPermission() {
        return SYS_ADMIN_PERMISSION;
    }
    public static String[] getProductOwnerPermission() {
        return PRODUCT_OWNER_PERMISSION;
    }


}
