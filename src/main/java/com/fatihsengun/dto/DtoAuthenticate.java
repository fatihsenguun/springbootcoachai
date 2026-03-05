package com.fatihsengun.dto;

import com.fatihsengun.entity.FitnessProfile;
import com.fatihsengun.enums.RoleType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DtoAuthenticate {

    public String accessToken;

    public String refreshToken;


}
