package com.fatihsengun.controller.impl;

import com.fatihsengun.controller.IRestAuthController;
import com.fatihsengun.dto.*;
import com.fatihsengun.service.impl.AuthServiceImpl;
import com.fatihsengun.service.impl.RefreshTokenServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestAuthControllerImpl implements IRestAuthController {

    @Autowired
    private AuthServiceImpl authService;

    @Autowired
    private RefreshTokenServiceImpl refreshTokenService;

    @Override
    @PostMapping("/register")
    public DtoRegister register(@Valid @RequestBody DtoRegisterUI authRequest) {
        return authService.register(authRequest);
    }

    @Override
    @PostMapping("/authenticate")
    public DtoAuthenticate authenticate(@Valid @RequestBody DtoAuthenticateUI dtoAuthenticateUI) {
        return authService.authenticate(dtoAuthenticateUI);
    }

    @Override
    @PostMapping("refresh_token")
    public DtoRefreshToken refresh(@RequestBody DtoRefreshTokenUI dtoRefreshTokenUI) {

        return refreshTokenService.refreshToken(dtoRefreshTokenUI);
    }
}
