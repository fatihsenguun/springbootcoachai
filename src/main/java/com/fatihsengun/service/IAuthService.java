package com.fatihsengun.service;


import com.fatihsengun.dto.*;

public interface IAuthService {

    public DtoRegister register(DtoRegisterUI dtoRegisterUI);

    public DtoAuthenticate authenticate(DtoAuthenticateUI dtoAuthenticateUI);
}
