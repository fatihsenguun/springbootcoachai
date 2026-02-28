package com.fatihsengun.controller.impl;

import com.fatihsengun.controller.IRestAuthController;
import com.fatihsengun.dto.*;
import com.fatihsengun.entity.RootResponseEntity;
import com.fatihsengun.service.impl.AuthServiceImpl;
import com.fatihsengun.service.impl.RefreshTokenServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestAuthControllerImpl extends RestRootResponseController implements IRestAuthController {

    @Autowired
    private AuthServiceImpl authService;

    @Autowired
    private RefreshTokenServiceImpl refreshTokenService;

    @Override
    @PostMapping("/register")
    public RootResponseEntity<DtoRegister> register(@Valid @RequestBody DtoRegisterUI authRequest) {
        return ok(authService.register(authRequest));
    }

    @Override
    @PostMapping("/authenticate")
    public RootResponseEntity<DtoAuthenticate> authenticate(@Valid @RequestBody DtoAuthenticateUI dtoAuthenticateUI) {
        return ok(authService.authenticate(dtoAuthenticateUI));
    }

    @Override
    @PostMapping("refresh_token")
    public RootResponseEntity<DtoRefreshToken> refresh(@RequestBody DtoRefreshTokenUI dtoRefreshTokenUI) {

        return ok(refreshTokenService.refreshToken(dtoRefreshTokenUI));
    }
}
