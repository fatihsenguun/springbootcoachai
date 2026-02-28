package com.fatihsengun.service;


import com.fatihsengun.dto.DtoUser;
import com.fatihsengun.jwt.AuthRequest;

public interface IAuthService {

    public DtoUser register(AuthRequest authRequest);
}
