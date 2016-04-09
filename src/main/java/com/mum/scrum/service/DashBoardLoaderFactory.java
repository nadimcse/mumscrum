package com.mum.scrum.service;

import com.mum.scrum.viewmodel.Login;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: nadim
 * Date: 4/8/16
 * Time: 9:34 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class DashBoardLoaderFactory {

    public Map<String, Object> loadDashboard(Login login) {

        DashBoard dashBoard = resolvedDashboard(login);
        return dashBoard.populateData(login);

    }

    abstract DashBoard resolvedDashboard(Login login);

}
