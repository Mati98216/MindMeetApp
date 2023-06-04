package com.example.mindmeetapp.controller;
import com.example.mindmeetapp.model.UserModel;
import com.example.mindmeetapp.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    public void testGetUsersList() throws Exception {
        UserModel user = new UserModel();
        user.setId(1L);
        user.setUsername("testuser");
        user.setPassword("testpassword");
        List<UserModel> userList = Collections.singletonList(user);
        when(userService.getAllUsers()).thenReturn(userList);
        mockMvc.perform(MockMvcRequestBuilders.get("/users"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(userList.size()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(user.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].username").value(user.getUsername()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].password").value(user.getPassword()));
    }
    @Test
    public void testGetUserById() throws Exception {
        UserModel user = new UserModel();
        user.setId(1L);
        user.setUsername("testuser");
        user.setPassword("testpassword");

        when(userService.getUserById(1L)).thenReturn(user);

        mockMvc.perform(MockMvcRequestBuilders.get("/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(user.getId()))
                .andExpect(jsonPath("$.username").value(user.getUsername()))
                .andExpect(jsonPath("$.password").value(user.getPassword()));
    }

    @Test
    public void testRegisterUser() throws Exception {
        UserModel user = new UserModel();
        user.setId(1L);
        user.setUsername("testuser");
        user.setPassword("testpassword");

        when(userService.createUser(any(UserModel.class))).thenReturn(user);

        mockMvc.perform(MockMvcRequestBuilders.post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\": 1, \"username\":\"testuser\",\"password\":\"testpassword\",\"surname\":\"Doe\",\"name\":\"John\",\"phoneNumber\":1234567890,\"email\":\"john@example.com\",\"isAdministrator\":false}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(user.getId()))
                .andExpect(jsonPath("$.username").value(user.getUsername()))
                .andExpect(jsonPath("$.password").value(user.getPassword()));
    }

    @Test
    public void testUpdateUser() throws Exception {
        UserModel user = new UserModel();
        user.setId(1L);
        user.setUsername("testuser");
        user.setPassword("testpassword");

        when(userService.createUser(any(UserModel.class))).thenReturn(user);

        mockMvc.perform(MockMvcRequestBuilders.put("/users/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\": 1, \"username\":\"testuser\",\"password\":\"testpassword\",\"surname\":\"Doe\",\"name\":\"John\",\"phoneNumber\":1234567890,\"email\":\"john@example.com\",\"isAdministrator\":false}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(user.getId()))
                .andExpect(jsonPath("$.username").value(user.getUsername()))
                .andExpect(jsonPath("$.password").value(user.getPassword()));
    }

    @Test
    public void testDeleteUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/users/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void testLoginUser() throws Exception {
        UserModel user = new UserModel();
        user.setId(1L);
        user.setUsername("testuser");
        user.setPassword("testpassword");

        when(userService.loginUser("testuser", "testpassword")).thenReturn(user);

        mockMvc.perform(MockMvcRequestBuilders.post("/users/login")
                        .param("username", "testuser")
                        .param("password", "testpassword"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(user.getId()))
                .andExpect(jsonPath("$.username").value(user.getUsername()))
                .andExpect(jsonPath("$.password").value(user.getPassword()));
    }
}