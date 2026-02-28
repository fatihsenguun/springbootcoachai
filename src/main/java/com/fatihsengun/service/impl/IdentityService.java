package com.fatihsengun.service.impl;

import com.fatihsengun.entity.User;
import com.fatihsengun.repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IdentityService {

    @Autowired
    private AuthRepository authRepository;

    public User getCurrentUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println(email);
        return authRepository.findByEmail(email).orElseThrow(() ->
                new RuntimeException("User Not found" + email));
    }

}
