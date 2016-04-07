package com.mum.scrum.utility;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;

/**
 * Created by 984609 on 4/7/2016.
 */
public class ValidationHandler {

    public static ResponseEntity<Object> handleFormValidation(BindingResult bindResult) {

        StringBuilder builder = new StringBuilder();
        List<FieldError> errors = bindResult.getFieldErrors();
        for (FieldError error : errors ) {
            builder.append(error.getField() + " : " + error.getDefaultMessage());
        }
        return new ResponseEntity<Object>(builder, HttpStatus.BAD_REQUEST);

    }
}
