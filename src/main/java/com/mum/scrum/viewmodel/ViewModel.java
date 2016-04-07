package com.mum.scrum.viewmodel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: nadim
 * Date: 4/6/16
 * Time: 5:19 PM
 * To change this template use File | Settings | File Templates.
 */
public class ViewModel {
      Map<String, Object> dataMap = new HashMap<>();

    public Map<String, Object> getDataMap() {
        return dataMap;
    }

    public void setDataMap(Map<String, Object> dataMap) {
        this.dataMap = dataMap;
    }
}
