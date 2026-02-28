package com.fatihsengun.controller;

import com.fatihsengun.dto.*;
import com.fatihsengun.entity.RootResponseEntity;

public interface IRestAuthController {
    public RootResponseEntity<DtoRegister> register(DtoRegisterUI dtoRegisterUI);

    public RootResponseEntity<DtoAuthenticate> authenticate(DtoAuthenticateUI dtoAuthenticateUI);

    public RootResponseEntity<DtoRefreshToken> refresh(DtoRefreshTokenUI dtoRefreshTokenUI);
}
