package com.boardgame.authservice.auth.requests;

import java.sql.Date;

import com.boardgame.authservice.auth.models.Gender;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
public class RegisterRequest { 

    @NotBlank(message = "Email address is empty")
    @Email(message = "Email address is not valid")
    private String email;

    @NotBlank(message = "Password is empty")
    @Size(min = 8, message = "Password must be at least 8 characters")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$", message = "Password must contain uppercase letter, lowercase letter and number")
    private String password;

    @Valid
    @NotNull(message = "Name is null")
    private RegisterName name;

    @NotBlank(message = "Phone number is empty")
    @Pattern(regexp = "\\d{10}", message = "Phone number must be a valid 10-digit string")
    private String phoneNumber;

    private Gender gender;

    private Date birthday;

    @Valid
    @NotBlank(message = "Address is empty")
    private String address;

    @Getter
    public class RegisterName {

        @NotBlank(message = "First name is empty")
        private String firstName;

        @NotBlank(message = "Last name is empty")
        private String lastName;
    }
}
