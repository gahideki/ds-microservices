package com.user.service;

import com.user.model.User;
import com.user.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;


    @Before
    public void setup() {
        User user = new User();
        user.setId(1L);
        user.setEmail("nina@gmail.com");

        when(userRepository.findByEmail(any())).thenReturn(Optional.of(user));
        when(userRepository.findById(any())).thenReturn(Optional.of(user));
    }

    @Test
    public void deveCarregarUmUsuarioAoBuscarPeloEmail() {
        User userFound = userService.getUserByEmail(any());

        assertNotNull(userFound);
        assertEquals("nina@gmail.com", userFound.getEmail());
    }

    @Test
    public void deveCarregarUmUsuarioAoBuscarPeloId() {
        User userFound = userService.getUserById(any());

        assertNotNull(userFound);
        assertEquals(Long.valueOf(1L), userFound.getId());
    }

    @Test
    public void naoDeveCarregarUmUsuarioAoBuscarPeloEmail() {
        when(userRepository.findByEmail(any())).thenReturn(Optional.empty());

        RuntimeException runtimeException = assertThrows(RuntimeException.class, () -> {
            userService.getUserByEmail(any());
        });
        assertEquals("User not found", runtimeException.getMessage());
    }

    @Test
    public void naoDeveCarregarUmUsuarioAoBuscarPeloId() {
        when(userRepository.findById(any())).thenReturn(Optional.empty());

        RuntimeException runtimeException = assertThrows(RuntimeException.class, () -> {
            userService.getUserById(any());
        });
        assertEquals("User not found", runtimeException.getMessage());
    }

}