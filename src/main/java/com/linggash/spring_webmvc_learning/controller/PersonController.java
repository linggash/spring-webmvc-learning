package com.linggash.spring_webmvc_learning.controller;

import com.linggash.spring_webmvc_learning.model.CreatePersonRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@Controller
public class PersonController {

    @PostMapping(path = "/person",consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> createPerson(@ModelAttribute @Valid CreatePersonRequest request,
                               BindingResult bindingResult) {

        List<FieldError> errors = bindingResult.getFieldErrors();

        if (!errors.isEmpty()) {
            //error

            errors.forEach(fieldError -> {
                System.out.println(fieldError.getField() + " : " + fieldError.getDefaultMessage());
            });

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("You send invalid data");
        }

        System.out.println(request);

        String response = "Success create person " +
                request.getFirstName() + " " +
                request.getMiddleName() + " " +
                request.getLastName() + " " +
                "with email " + request.getEmail() + " " +
                "and phone " + request.getPhone() + " " +
                "with address " +
                request.getAddress().getStreet() + ", " +
                request.getAddress().getCity() + ", " +
                request.getAddress().getCountry() + " " +
                request.getAddress().getPostalCode();

        return ResponseEntity.ok(response);
    }

}
