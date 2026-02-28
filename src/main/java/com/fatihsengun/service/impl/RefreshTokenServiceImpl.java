package com.fatihsengun.service.impl;

import com.fatihsengun.dto.DtoRefreshToken;
import com.fatihsengun.dto.DtoRefreshTokenUI;
import com.fatihsengun.entity.RefreshToken;
import com.fatihsengun.entity.User;
import com.fatihsengun.jwt.JwtService;
import com.fatihsengun.repository.RefreshTokenRepository;
import com.fatihsengun.repository.UserRepository;
import com.fatihsengun.service.IRefreshTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class RefreshTokenServiceImpl implements IRefreshTokenService {

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @Autowired
    private JwtService jwtService;


    @Override
    @Transactional
    public RefreshToken saveRefreshToken(User user) {
        deleteRefresh(user);

        RefreshToken newRefreshToken = new RefreshToken();
        newRefreshToken.setUser(user);
        newRefreshToken.setRefreshToken(UUID.randomUUID().toString());
        newRefreshToken.setExpireDate(LocalDateTime.now().plusDays(5));

        return refreshTokenRepository.save(newRefreshToken);
    }

    public void deleteRefresh(User user) {
        RefreshToken refreshToken = refreshTokenRepository.findRefreshTokenByUserId(user.getId())
                .orElseThrow(() -> new RuntimeException("Refresh Token Not Found"));

        refreshTokenRepository.delete(refreshToken);

    }

    public boolean isRefreshTokenExpired(LocalDateTime expiredDate) {
        return LocalDateTime.now().isAfter(expiredDate);
    }


    public DtoRefreshToken refreshToken(DtoRefreshTokenUI dtoRefreshTokenUI) {
        RefreshToken refresh = refreshTokenRepository
                .findRefreshTokenByRefreshToken(dtoRefreshTokenUI.getRefreshToken())
                .orElseThrow(() -> new RuntimeException("Token Not Found"));

        if (isRefreshTokenExpired(refresh.getExpireDate())) {
            throw new RuntimeException("Token Expired");
        }
        String accessToken = jwtService.generateToken(refresh.getUser());
        RefreshToken savedRefreshToken = saveRefreshToken(refresh.getUser());
        refreshTokenRepository.delete(refresh);
        return new DtoRefreshToken(accessToken, savedRefreshToken.getRefreshToken());

    }
}
