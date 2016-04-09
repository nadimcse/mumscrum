package com.mum.scrum.restController;

import com.mum.scrum.service.LoginService;
import com.mum.scrum.viewmodel.Login;
import com.mum.scrum.viewmodel.ViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
public class LoginController {

    @Autowired
    private LoginService loginService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ResponseEntity<Void> getLoginUser() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

        @RequestMapping(value = "/login", produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE} , method = RequestMethod.POST)
    public ResponseEntity<ViewModel> loginUser(@Valid @RequestBody Login login, BindingResult bindResult) {
        ViewModel viewModel = new ViewModel();
        if (bindResult.hasErrors()) {
            StringBuilder builder = new StringBuilder();
            List<FieldError> errors = bindResult.getFieldErrors();
            for (FieldError error : errors ) {
                builder.append(error.getField() + " : " + error.getDefaultMessage());
            }
            viewModel.getDataMap().put("errorMessage", builder.toString());
            return new ResponseEntity<>(viewModel, HttpStatus.BAD_REQUEST);
        }
        if (!loginService.authenticateLogin(login)) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        ///TODO  load dashboard
        Map<String, Object> map  = loginService.handleDashBoard(login);
        viewModel.setDataMap(map);

        return new ResponseEntity<>(viewModel, HttpStatus.OK);
    }
}
