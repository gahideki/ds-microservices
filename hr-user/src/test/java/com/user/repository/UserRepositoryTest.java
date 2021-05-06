package com.user.repository;

import com.user.model.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class UserRepositoryTest {

    @Mock
    private UserRepository userRepository;

    @Before
    public void setup() {
        User user = new User();
        user.setEmail("nina@gmail.com");

        when(userRepository.findByEmail(any())).thenReturn(Optional.of(user));
    }

    @Test
    public void deveCarregarUmUsuarioAoBuscarPeloEmail() {
        Optional<User> user = userRepository.findByEmail("nina@gmail.com");

        assertNotNull(user);
        assertEquals("nina@gmail.com", user.get().getEmail());
    }

    @Test
    public void naoDeveCarregarUmUsuarioAoBuscarPeloEmail() {
        when(userRepository.findByEmail(any())).thenReturn(Optional.empty());
        Optional<User> user = userRepository.findByEmail("nina@gmail.com");

        assertTrue(user.isEmpty());
    }

}