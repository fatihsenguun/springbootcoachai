package com.fatihsengun.service.impl;

import com.fatihsengun.dto.DtoUser;
import com.fatihsengun.repository.UserRepository;
import com.fatihsengun.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public DtoUser findUserById(UUID id) {
        return null;
    }
}
