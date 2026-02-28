package com.fatihsengun.controller.impl;

import com.fatihsengun.controller.IRestAuthController;
import com.fatihsengun.dto.DtoUser;
import com.fatihsengun.jwt.AuthRequest;
import com.fatihsengun.service.impl.AuthServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestAuthControllerImpl implements IRestAuthController {

    @Autowired
    private AuthServiceImpl authService;

    @Override
    @PostMapping("/register")
    public DtoUser register(@RequestBody AuthRequest authRequest) {
        return authService.register(authRequest);
    }
}
