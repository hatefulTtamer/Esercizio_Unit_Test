package co.develhope.esercizio_unit_test.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {
    @Autowired
    private UserController userController;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void contextLoads() {
        assertThat(userController).isNotNull();
    }

    @Test
    void testUserController_createUser() throws Exception {
        this.mockMvc.perform(post("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                "firstName": "Gino",
                                "lastName": "De Ginis",
                                "age": 38
                                }
                                """))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNumber());
    }

    @Test
    void testUserController_findAllUsers() throws Exception {
        this.mockMvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                        "firstName": "Gino",
                        "lastName": "De Ginis",
                        "age": 38
                        }
                        """));

        this.mockMvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                        "firstName": "Gino",
                        "lastName": "De Ginis",
                        "age": 38
                        }
                        """));

        this.mockMvc.perform(get("/user"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[1].id").isNumber());
    }

    @Test
    void testUserController_findUserById() throws Exception {
        this.mockMvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                        "firstName": "Gino",
                        "lastName": "De Ginis",
                        "age": 38
                        }
                        """));

        this.mockMvc.perform(get("/user/5"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNumber());
    }

    @Test
    void testUserController_updateUser() throws Exception {
        this.mockMvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                        "firstName": "Gino",
                        "lastName": "De Ginis",
                        "age": 38
                        }
                        """));

        this.mockMvc.perform(put("/user/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                        "firstName": "Gino",
                        "lastName": "Ginetto",
                        "age": 38
                        }
                        """))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNumber());
    }

    @Test
    void testUserController_deleteUser() throws Exception {

        this.mockMvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                        "firstName": "Gino",
                        "lastName": "De Ginis",
                        "age": 38
                        }
                        """));

        this.mockMvc.perform(delete("/user/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").doesNotExist());
    }


}
