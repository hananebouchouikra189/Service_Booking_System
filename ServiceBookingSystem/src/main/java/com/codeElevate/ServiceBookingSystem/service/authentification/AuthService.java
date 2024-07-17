package com.codeElevate.ServiceBookingSystem.service.authentification;

import com.codeElevate.ServiceBookingSystem.dto.SignupRequestDTO;
import com.codeElevate.ServiceBookingSystem.dto.UserDto;

public interface AuthService {

    public Boolean presentByEmail(String email);
    public UserDto signupClient(SignupRequestDTO signupRequestDTO);
    public UserDto signupCompany(SignupRequestDTO signupRequestDTO);
}
