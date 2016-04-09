package com.mum.scrum.restController;

import com.mum.scrum.config.ScrumConfig;
import com.mum.scrum.model.Message;
import com.mum.scrum.model.User;
import com.mum.scrum.service.UserService;
import com.mum.scrum.viewmodel.ViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: nadim
 * Date: 4/4/16
 * Time: 8:47 PM
 * To change this template use File | Settings | File Templates.
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private Environment env;

    @RequestMapping("/hello/{player}")
    public Message message(@PathVariable String player) {

        // userDao.saveJpaContact();
        Message msg = new Message(player, "Hello " + player);
        return msg;
    }

    @RequestMapping(value = "/user/{userId}", produces = {MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.GET)
    public ResponseEntity<ViewModel> getUser(@PathVariable("userId") long userId) {
        ///TODO has role & permission
        //userService.validateUser();

        Map<String, Object> dataMap = userService.handleGetUser(userId);
        ViewModel viewModel = new ViewModel();
        viewModel.setDataMap(dataMap);
        return new ResponseEntity<ViewModel>(viewModel, HttpStatus.OK);

    }

    @RequestMapping(value = "/user", produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.POST)
    public ResponseEntity<ViewModel> createUser(@Valid @RequestBody User user, BindingResult bindResult) {
        ViewModel viewModel = new ViewModel();
        //form validation
        if (bindResult.hasErrors()) {
            StringBuilder builder = new StringBuilder();
            List<FieldError> errors = bindResult.getFieldErrors();
            for (FieldError error : errors) {
                builder.append(error.getField()).append(" : ").append(error.getDefaultMessage());
            }

            if (StringUtils.isEmpty(user.getPassword())) {
                builder.append("password" + " : " + "password is empty.");
            }
            viewModel.getDataMap().put("errorMessage", builder.toString());
            return new ResponseEntity<>(viewModel, HttpStatus.BAD_REQUEST);
        }

        //business case validation
        Map<String, Object> validationMap = userService.validateUsrCreation(user);
        if (validationMap.isEmpty()) {     //error happens
            viewModel.setDataMap(validationMap);
            return new ResponseEntity<>(viewModel, HttpStatus.BAD_REQUEST);
        }

        userService.persistUser(user);
        String successMsg = "User has been created successfully!";
        viewModel.getDataMap().put("message", successMsg);

        //TODO to uri
        return new ResponseEntity<ViewModel>(viewModel, HttpStatus.CREATED);
        //  HttpHeaders headers = new HttpHeaders();
        //   headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri());
    }

    @RequestMapping(value = "/user", produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.PUT)
    public ResponseEntity<ViewModel> updateUser(@PathVariable("id") long id, @Valid @RequestBody User user, BindingResult bindResult) {
        ViewModel viewModel = new ViewModel();
        //form validation
        if (bindResult.hasErrors()) {
            StringBuilder builder = new StringBuilder();
            List<FieldError> errors = bindResult.getFieldErrors();
            for (FieldError error : errors) {
                builder.append(error.getField() + " : " + error.getDefaultMessage());
            }
            viewModel.getDataMap().put("errorMessage", builder.toString());
            return new ResponseEntity<>(viewModel, HttpStatus.BAD_REQUEST);
        }

        ////password field is handled seperately
        if (!StringUtils.isEmpty(user.getPassword())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        userService.updateUser(id, user);

        String successMsg = "User has been updated successfully!";
        viewModel.getDataMap().put("message", successMsg);
        return new ResponseEntity<>(viewModel, HttpStatus.OK);
    }


}
