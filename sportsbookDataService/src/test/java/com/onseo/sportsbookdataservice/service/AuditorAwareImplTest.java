package com.onseo.sportsbookdataservice.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class AuditorAwareImplTest {

    @Autowired
    private AuditorAwareImpl auditorAware;

    @Test
    public void shouldReturnAdminUserAsCurrentAuditor() {
        Optional<String> currentAuditor = auditorAware.getCurrentAuditor();
        String expectedAuditor = "AdminUser";
        assertEquals(expectedAuditor, currentAuditor.orElse(null));
    }

}