package com.mum.scrum.service;

import com.mum.scrum.viewmodel.Login;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: nadim
 * Date: 4/9/16
 * Time: 12:17 AM
 * To change this template use File | Settings | File Templates.
 */
@Service("productOwnerDashBoard")
public class ProductOwnerDashBoard implements DashBoard {

    @Override
    public Map<String, Object> populateData(Login login) {
        return new HashMap<>();
    }
}
