package com.boardgame.userservice.requests;

import java.sql.Date;

import com.boardgame.userservice.models.Gender;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.Getter;

@Data
public class CreateUserRequest {
    
    @Valid
    @NotNull(message = "Name is null")
    private CreateUserName name;

    @NotBlank(message = "Phone number is empty")
    @Pattern(regexp = "\\d{10}", message = "Phone number must be a valid 10-digit string")
    private String phoneNumber;

    private Gender gender;

    private Date birthday;

    @Valid
    @NotBlank(message = "Address is empty")
    private String address;

    @Getter
    public class CreateUserName {

        @NotBlank(message = "First name is empty")
        private String firstName;

        @NotBlank(message = "Last name is empty")
        private String lastName;
    }
}
