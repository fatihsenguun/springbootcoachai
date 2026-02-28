package com.fatihsengun.service;

import com.fatihsengun.entity.RefreshToken;
import com.fatihsengun.entity.User;

public interface IRefreshTokenService {

    public RefreshToken saveRefreshToken(User user);

}
