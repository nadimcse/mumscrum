package com.mum.scrum.service;

import com.mum.scrum.model.Sprint;

import java.util.List;

/**
 * Created by 984609 on 4/12/2016.
 */
public interface SprintService {
    void persist(Sprint sprint);
    List<String> validateSprintCreation(Sprint sprint);
    List<String> validateSprintUpdate(Sprint sprint);
    void updateSprint(long sprintId, Sprint sprint);;
}
