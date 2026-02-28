package com.fatihsengun.service;


import com.fatihsengun.dto.DtoUser;
import com.fatihsengun.dto.DtoUserUI;

public interface IUserService {

    public DtoUser saveUser(DtoUserUI dtoUserUI);
}
