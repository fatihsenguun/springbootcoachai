package com.fatihsengun.service.impl;

import com.fatihsengun.dto.*;
import com.fatihsengun.entity.User;
import com.fatihsengun.enums.RoleType;
import com.fatihsengun.jwt.JwtService;
import com.fatihsengun.mapper.IGlobalMapper;
import com.fatihsengun.repository.UserRepository;
import com.fatihsengun.service.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements IAuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private IGlobalMapper globalMapper;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public DtoRegister register(DtoRegisterUI dtoRegisterUI) {

        User user = new User();
        user.setRole(RoleType.USER);
        user.setFirstName(dtoRegisterUI.getFirstName());
        user.setLastName(dtoRegisterUI.getLastName());
        user.setPassword(passwordEncoder.encode(dtoRegisterUI.getPassword()));
        user.setEmail(dtoRegisterUI.getEmail());

        return globalMapper.toDtoRegister(userRepository.save(user));
    }

    @Override
    public DtoAuthenticate authenticate(DtoAuthenticateUI dtoAuthenticateUI) {

        try {
            UsernamePasswordAuthenticationToken auth =
                    new UsernamePasswordAuthenticationToken(dtoAuthenticateUI.getEmail(), dtoAuthenticateUI.getPassword());
            authenticationProvider.authenticate(auth);
            User user = userRepository.findByEmail(dtoAuthenticateUI.getEmail())
                    .orElseThrow(() -> new RuntimeException("user not found"));
            String accessToken = jwtService.generateToken(user);

            return new DtoAuthenticate(accessToken, null, user.getFirstName(), user.getLastName(), user.getRole());

        } catch (Exception e) {
            System.out.println("Username or password wrong");
        }
        return null;
    }
}
