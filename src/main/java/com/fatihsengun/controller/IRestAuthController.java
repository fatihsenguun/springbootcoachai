package com.fatihsengun.controller;

import com.fatihsengun.dto.*;

public interface IRestAuthController {
    public DtoRegister register(DtoRegisterUI dtoRegisterUI);

    public DtoAuthenticate authenticate(DtoAuthenticateUI dtoAuthenticateUI);

    public DtoRefreshToken refresh(DtoRefreshTokenUI dtoRefreshTokenUI);
}
