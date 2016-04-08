package com.mum.scrum.restController;

import com.mum.scrum.service.LoginService;
import com.mum.scrum.viewmodel.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: nadim
 * Date: 4/6/16
 * Time: 4:54 PM
 * To change this template use File | Settings | File Templates.
 */
@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<Object> createUser(@Valid @RequestBody Login login, BindingResult bindResult) {

        if (bindResult.hasErrors()) {
            StringBuilder builder = new StringBuilder();
            List<FieldError> errors = bindResult.getFieldErrors();
            for (FieldError error : errors ) {
                builder.append(error.getField() + " : " + error.getDefaultMessage());
            }
            return new ResponseEntity<Object>(builder, HttpStatus.BAD_REQUEST);
        }
        if (!loginService.authenticateLogin(login)) {
            return new ResponseEntity<Object>("Login failed!!!", HttpStatus.FORBIDDEN);
        }
        ///TODO  load dashboard
        return new ResponseEntity<Object>("User successfully logged in!", HttpStatus.OK);
    }
}
