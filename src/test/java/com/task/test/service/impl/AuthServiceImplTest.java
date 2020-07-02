package com.task.test.service.impl;

import com.task.test.service.AuthService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AuthServiceImplTest {
    @Autowired
    private AuthService authService;

    @Test
    void getToken() {
        assertNotNull(authService.getToken());
    }
}