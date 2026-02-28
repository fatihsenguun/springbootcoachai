package com.fatihsengun.service.impl;

import com.fatihsengun.dto.DtoUser;
import com.fatihsengun.entity.User;
import com.fatihsengun.enums.RoleType;
import com.fatihsengun.jwt.AuthRequest;
import com.fatihsengun.mapper.IGlobalMapper;
import com.fatihsengun.repository.UserRepository;
import com.fatihsengun.service.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements IAuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private IGlobalMapper globalMapper;

    @Override
    public DtoUser register(AuthRequest authRequest) {

        User user = new User();
        user.setRole(RoleType.USER);
        user.setPassword(authRequest.getPassword());
        user.setEmail(authRequest.getEmail());

        return globalMapper.toDtoUser(userRepository.save(user));
    }
}
