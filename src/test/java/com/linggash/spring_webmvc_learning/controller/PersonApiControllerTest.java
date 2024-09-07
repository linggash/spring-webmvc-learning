package com.linggash.spring_webmvc_learning.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.linggash.spring_webmvc_learning.model.CreatePersonRequest;
import com.linggash.spring_webmvc_learning.model.CreateSocialMediaRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.MockMvcBuilder.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

@SpringBootTest
@AutoConfigureMockMvc
class PersonApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCreatePerson() throws Exception {
        CreatePersonRequest request = new CreatePersonRequest();
        request.setFirstName("Bambang");
        request.setMiddleName("Udin");
        request.setLastName("Ucok");
        request.setEmail("bambang@email.com");
        request.setPhone("120013");
        request.setHobbies(List.of("Running, Cycling, Gaming"));
        request.setSocialMedias(List.of(
                new CreateSocialMediaRequest("Facebook", "facebook.com/bambang"),
                new CreateSocialMediaRequest("Instagram", "instagram.com/bambang")
        ));

        String jsonRequest = objectMapper.writeValueAsString(request);

        mockMvc.perform(
                post("/api/person")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(jsonRequest)
        ).andExpectAll(
                status().isOk(),
                content().json(jsonRequest)
        );
    }
}