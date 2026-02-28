package com.fatihsengun.service;

import com.fatihsengun.dto.DtoUser;

import java.util.UUID;

public interface IUserService {

    public DtoUser findUserById(UUID id);
}
