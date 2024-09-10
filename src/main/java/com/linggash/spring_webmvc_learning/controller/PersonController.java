package com.linggash.spring_webmvc_learning.controller;

import com.linggash.spring_webmvc_learning.model.CreatePersonRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class PersonController {

    @PostMapping(path = "/person",consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public String createPerson(@ModelAttribute @Valid CreatePersonRequest request) {

        System.out.println(request);

        return "Success create person " +
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
    }

}
