package com.mum.scrum.restController;

import com.mum.scrum.model.Project;
import com.mum.scrum.model.Sprint;
import com.mum.scrum.service.FormValidatorService;
import com.mum.scrum.service.SprintService;
import com.mum.scrum.utility.Utility;
import com.mum.scrum.viewmodel.ViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: nadim
 * Date: 4/6/16
 * Time: 4:54 PM
 * To change this template use File | Settings | File Templates.
 */
@RestController
public class SprintController {

    @Autowired
    private SprintService sprintService;

    @Autowired
    private FormValidatorService formValidatorService;

    @RequestMapping(value = "/sprint", produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.POST)
    public ResponseEntity<ViewModel> createSprint(@Valid @RequestBody Sprint sprint, BindingResult bindResult) {
        //form validation
        if (bindResult.hasErrors()) {
            List<String> errorList = formValidatorService.doFormValidation(bindResult);
            return new ResponseEntity<>(Utility.populateViewModel(Utility.ERROR_STATUS_CODE, errorList), HttpStatus.BAD_REQUEST);
        }

        List<String> validations = sprintService.validateSprintCreation(sprint);
        if (hasAnyLogicalError(validations)) {
            return new ResponseEntity<>(Utility.populateViewModel(Utility.ERROR_STATUS_CODE, validations), HttpStatus.BAD_REQUEST);
        }
        sprintService.persist(sprint);
        Map<String, Object> map = new HashMap<>();
        map.put("sprintList", Arrays.asList(sprint));
        //TODO to uri
        return new ResponseEntity<>(
                Utility.populateViewModel(Utility.SUCCESS_STATUS_CODE, Arrays.asList("Project has been created successfully!"), map),
                HttpStatus.CREATED);

    }

    @ResponseBody
    @RequestMapping(value = "/sprint/{sprintId}", produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.PUT)
    public ResponseEntity<ViewModel> updateSprint(@PathVariable("sprintId") long sprintId, @Valid @RequestBody Sprint sprint, BindingResult bindResult) {
        //form validation
        if (bindResult.hasErrors()) {
            List<String> errorList = formValidatorService.doFormValidation(bindResult);
            return new ResponseEntity<>(Utility.populateViewModel(Utility.ERROR_STATUS_CODE, errorList), HttpStatus.BAD_REQUEST);
        }

        List<String> validations = sprintService.validateSprintUpdate(sprint);
        if (hasAnyLogicalError(validations)) {
            return new ResponseEntity<>(Utility.populateViewModel(Utility.ERROR_STATUS_CODE, validations), HttpStatus.BAD_REQUEST);
        }
        sprintService.updateSprint(sprintId, sprint);
        Map<String, Object> map = new HashMap<>();
        map.put("sprintList", Arrays.asList(sprint));
        //TODO to uri
        return new ResponseEntity<>(
                Utility.populateViewModel(Utility.SUCCESS_STATUS_CODE, Arrays.asList("Sprint has been updated successfully!"), map),
                HttpStatus.OK);

    }

    private boolean hasAnyLogicalError(List<String> validationList) {
        return (validationList != null && validationList.size() > 0);
    }


}
