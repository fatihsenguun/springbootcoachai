package com.fatihsengun.dto;

import com.fatihsengun.enums.RoleType;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public class DtoUserUI {
    private String email;

    private String password;

    private String fullName;

    private RoleType role;

}
