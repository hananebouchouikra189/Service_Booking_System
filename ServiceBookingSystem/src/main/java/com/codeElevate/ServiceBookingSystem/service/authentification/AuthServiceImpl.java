package com.codeElevate.ServiceBookingSystem.service.authentification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codeElevate.ServiceBookingSystem.dto.SignupRequestDTO;
import com.codeElevate.ServiceBookingSystem.dto.UserDto;
import com.codeElevate.ServiceBookingSystem.entity.User;
import com.codeElevate.ServiceBookingSystem.enums.UserRole;
import com.codeElevate.ServiceBookingSystem.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
public class AuthServiceImpl implements AuthService{

	@Autowired
	private UserRepository userRepository;
	public UserDto signupClient(SignupRequestDTO signupRequestDTO) {
		User user =new User();
		user.setName(signupRequestDTO.getName());
		user.setLastname(signupRequestDTO.getLastname());
		user.setEmail(signupRequestDTO.getEmail());
		user.setPhone(signupRequestDTO.getPhone());
		user.setPassword(new BCryptPasswordEncoder().encode(signupRequestDTO.getPassword()));
		user.setRole(UserRole.CLIENT);
		return userRepository.save(user).getDto();
	}
	public Boolean presentByEmail(String email){
				return userRepository.findByEmail(email)!=null;
	}

	public UserDto signupCompany(SignupRequestDTO signupRequestDTO) {
		User user =new User();
		user.setName(signupRequestDTO.getName());
		user.setEmail(signupRequestDTO.getEmail());
		user.setPhone(signupRequestDTO.getPhone());
		user.setPassword(new BCryptPasswordEncoder().encode(signupRequestDTO.getPassword()));
		user.setRole(UserRole.COMPANY);
		return userRepository.save(user).getDto();
	}
	
}
