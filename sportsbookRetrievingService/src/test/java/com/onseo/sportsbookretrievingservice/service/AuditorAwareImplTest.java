package com.onseo.sportsbookretrievingservice.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class AuditorAwareImplTest {

    @Autowired
    private AuditorAwareImpl auditorAwareImpl;

    @Test
    void shouldReturnAdminUserFromGetCurrentAuditor() {
        Optional<String> currentAuditor = auditorAwareImpl.getCurrentAuditor();
        assertTrue(currentAuditor.isPresent());
        assertEquals("AdminUser", currentAuditor.get());
    }
}