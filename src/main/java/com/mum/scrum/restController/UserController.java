package com.mum.scrum.restController;

import com.mum.scrum.model.Message;
import com.mum.scrum.model.User;
import com.mum.scrum.service.UserService;
import com.mum.scrum.utility.ValidationHandler;
import com.mum.scrum.viewmodel.ViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Locale;

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

    @RequestMapping("/hello/{player}")
    public Message message(@PathVariable String player) {

        // userDao.saveJpaContact();

        Message msg = new Message(player, "Hello " + player);
        return msg;
    }

    @RequestMapping(value = "/user/{userId}", method = RequestMethod.GET)
    public ResponseEntity<ViewModel> getUser(@PathVariable("userId") long userId) {
        ///TODO has role & permission
        //userService.validateUser();

        ViewModel viewModel =  userService.handleGetUser(userId);
        return new ResponseEntity<ViewModel>(viewModel, HttpStatus.OK);

    }

    @RequestMapping(value = "/createuser", method = RequestMethod.POST)
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user,  BindingResult bindResult) {

        if (bindResult.hasErrors()) {
            StringBuilder builder = new StringBuilder();
            List<FieldError> errors = bindResult.getFieldErrors();
            for (FieldError error : errors ) {
                builder.append(error.getField() + " : " + error.getDefaultMessage());
            }
            return new ResponseEntity<Object>(builder, HttpStatus.BAD_REQUEST);
        }
        ///TODO has role & permission
         //userService.validateUser()

         userService.persistUser(user);
      //  HttpHeaders headers = new HttpHeaders();
     //   headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri());
        return new ResponseEntity<Object>(HttpStatus.CREATED);
    }


}
