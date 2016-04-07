package com.mum.scrum.restController;

import com.mum.scrum.dao.UserDao;
import com.mum.scrum.model.Message;
import com.mum.scrum.model.User;
import com.mum.scrum.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value = "/getuser/{userId}", method = RequestMethod.GET)
    public ResponseEntity<User> getUser(@PathVariable("userId") long userId) {
        ///TODO has role & permission
        //userService.validateUser();

        User user =  userService.getUser(userId);
        return new ResponseEntity<User>(user, HttpStatus.OK);

    }

    @RequestMapping(value = "/createuser", method = RequestMethod.POST)
    public ResponseEntity<Void> createUser(@RequestBody User user) {
        ///TODO has role & permission
         //userService.validateUser()

         userService.persistUser(user);
      //  HttpHeaders headers = new HttpHeaders();
     //   headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri());
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }


}
