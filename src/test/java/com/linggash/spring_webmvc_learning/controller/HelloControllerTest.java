package com.linggash.spring_webmvc_learning.controller;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.MockMvcBuilder.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

@SpringBootTest
@AutoConfigureMockMvc
class HelloControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void helloGuest() throws Exception {
        mockMvc.perform(
                get("/hello")
        ).andExpectAll(
                status().isOk(),
                content().string(Matchers.containsString("Hello Guest"))
        );
    }

    @Test
    void helloName() throws Exception {
        mockMvc.perform(
                get("/hello").queryParam("name", "Bambang")
        ).andExpectAll(
                status().isOk(),
                content().string(Matchers.containsString("Hello Bambang"))
        );
    }


    @Test
    void helloPost() throws Exception {
        mockMvc.perform(
                post("/hello").queryParam("name", "Bambang")
        ).andExpectAll(
                status().isMethodNotAllowed()
        );
    }

    @Test
    void helloView() throws Exception {
        mockMvc.perform(
                get("/web").queryParam("name", "Bambang")
        ).andExpectAll(
                status().isOk(),
                content().string(Matchers.containsString("View Learning")),
                content().string(Matchers.containsString("Hello Bambang"))
        );
    }
}