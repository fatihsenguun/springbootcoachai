package com.fatihsengun.controller;

import com.fatihsengun.dto.DtoUser;
import com.fatihsengun.entity.RootResponseEntity;

public interface IRestUserController {
    public RootResponseEntity<DtoUser> getUser();
}
