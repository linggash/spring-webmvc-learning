package com.linggash.spring_webmvc_learning.controller;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.MockMvcBuilder.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

@SpringBootTest
@AutoConfigureMockMvc
class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testCreatePerson() throws Exception {
        mockMvc.perform(
                post("/person")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("firstName", "Bambang")
                        .param("middleName", "Udin")
                        .param("lastName", "Ucok")
                        .param("email", "bambang@email.com")
                        .param("phone", "88149194")
                        .param("address.street", "Jl Rusak")
                        .param("address.city", "Depok")
                        .param("address.country", "Indonesia")
                        .param("address.postalCode", "12033")
                        .param("hobbies[0]", "Running")
                        .param("hobbies[0]", "Cycling")
                        .param("hobbies[0]", "Coding")
                        .param("hobbies[0]", "Gaming")
                        .param("socialMedias[0].name", "Facebook")
                        .param("socialMedias[0].location", "facebook.com/BambangUdin")
                        .param("socialMedias[1].name", "Instagram")
                        .param("socialMedias[1].location", "instagram.com/BambangUdin")
        ).andExpectAll(
                status().isOk(),
                content().string(Matchers.containsString("Success create person Bambang Udin Ucok " +
                        "with email bambang@email.com " +
                        "and phone 88149194 " +
                        "with address Jl Rusak, Depok, Indonesia 12033"
                ))
        );
    }

    @Test
    void testCreatePersonValidationError() throws Exception {
        mockMvc.perform(
                post("/person")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("middleName", "Udin")
                        .param("lastName", "Ucok")
                        .param("address.street", "Jl Rusak")
                        .param("address.city", "Depok")
                        .param("address.country", "Indonesia")
                        .param("address.postalCode", "12033")
                        .param("hobbies[0]", "Running")
                        .param("hobbies[0]", "Cycling")
                        .param("hobbies[0]", "Coding")
                        .param("hobbies[0]", "Gaming")
                        .param("socialMedias[0].name", "Facebook")
                        .param("socialMedias[0].location", "facebook.com/BambangUdin")
                        .param("socialMedias[1].name", "Instagram")
                        .param("socialMedias[1].location", "instagram.com/BambangUdin")
        ).andExpectAll(
                status().isBadRequest(),
                content().string(Matchers.containsString("You send invalid data"))
        );
    }
}