package com.linggash.spring_webmvc_learning.controller;

import jakarta.servlet.http.Cookie;
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
class AuthControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

    @Test
    void testLoginSuccess() throws Exception {
        mockMvc.perform(
                post("/auth/login")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("username", "bambang")
                        .param("password", "secret")
        ).andExpectAll(
                status().isOk(),
                content().string(Matchers.containsString("OK")),
                cookie().value("username", Matchers.containsString("bambang"))
        );
    }

    @Test
    void testLoginFailed() throws Exception {
        mockMvc.perform(
                post("/auth/login")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("username", "bambang")
                        .param("password", "hahaha")
        ).andExpectAll(
                status().isUnauthorized(),
                content().string(Matchers.containsString("KO"))
        );
    }

    @Test
    void testGetUserCookie() throws Exception {
        mockMvc.perform(
                get("/auth/user")
                        .cookie(new Cookie("username", "bambang"))
        ).andExpectAll(
                status().isOk(),
                content().string(Matchers.containsString("Hello bambang"))
        );
    }
}