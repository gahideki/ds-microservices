package com.user.controller;

import com.user.model.User;
import com.user.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Before
    public void setup() {
        User user = new User();
        user.setName("Nina");
        user.setEmail("nina@gmail.com");

        given(userService.getUserByEmail(any())).willReturn(user);
    }

    @Test
    public void deveRetornar200QuandoEncontrarUsuarioPorEmail() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/users/search")
                .param("email", "nina@gmail.com")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.name", is("Nina")))
                .andExpect(jsonPath("$.email", is("nina@gmail.com")));
    }

    @Test
    public void deveRetornar400QuandoNaoEncontrarUsuarioPorEmail() throws Exception {
        given(userService.getUserByEmail(any())).willThrow(RuntimeException.class);

        mockMvc.perform(MockMvcRequestBuilders.get("/users/search")
                .param("email", "nina@gmail.com")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(result -> result.getResponse().equals(null));

        assertThrows(RuntimeException.class, () -> {
            userService.getUserByEmail(any());
        });
    }

}