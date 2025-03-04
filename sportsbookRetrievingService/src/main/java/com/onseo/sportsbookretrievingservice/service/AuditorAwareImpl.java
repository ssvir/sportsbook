package com.onseo.sportsbookretrievingservice.service;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuditorAwareImpl implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        // Normally fetch from Spring Security context
        return Optional.of("AdminUser"); // Hardcoded for demo
    }
}
