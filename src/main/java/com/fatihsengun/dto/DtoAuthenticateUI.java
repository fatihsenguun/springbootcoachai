package com.fatihsengun.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DtoAuthenticateUI {
    @NotBlank(message = "The email field cannot be left blank.")
    @Email(message = "Please provide a valid email address.")
    @Size(max = 50, message = "Email cannot exceed 50 characters.")
    private String email;

    @NotBlank(message = "The password field cannot be left blank.")
    @Size(min = 8, max = 30, message = "Password must be between 8 and 30 characters.")
    private String password;


}
