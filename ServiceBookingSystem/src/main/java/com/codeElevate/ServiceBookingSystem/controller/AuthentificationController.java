package com.codeElevate.ServiceBookingSystem.controller;
import com.codeElevate.ServiceBookingSystem.entity.User;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.codeElevate.ServiceBookingSystem.dto.AuthenticationRequest;
import com.codeElevate.ServiceBookingSystem.dto.SignupRequestDTO;
import com.codeElevate.ServiceBookingSystem.dto.UserDto;
import com.codeElevate.ServiceBookingSystem.repository.UserRepository;
import com.codeElevate.ServiceBookingSystem.service.authentification.AuthService;
import com.codeElevate.ServiceBookingSystem.service.jwt.UserDetailsServiceImpl;
import com.codeElevate.ServiceBookingSystem.util.JwtUtil;

import jakarta.servlet.http.HttpServletResponse;

@RestController
public class AuthentificationController {

    @Autowired
    private AuthService authService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    UserDetailsServiceImpl  userDetailsService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtUtil jwtUtil;
    public static final String TOKEN_PREFEX="Bearer ";
    public static String HEADER_STRING="Authorization";
    
    @PostMapping("/client/sign-up")
    public ResponseEntity<?> signupClient(@RequestBody SignupRequestDTO signupRequestDTO) {
        // Appeler le service d'authentification pour enregistrer le client
        // authService.signup(signupRequestDTO);
    	if(authService.presentByEmail(signupRequestDTO.getEmail())) {
    		return new ResponseEntity<>("Client already exists with this email ",HttpStatus.NOT_ACCEPTABLE);
    	}
       UserDto createUser=authService.signupClient(signupRequestDTO);
        return new ResponseEntity<>(createUser,HttpStatus.OK);
    }
    @PostMapping("/company/sign-up")
    public ResponseEntity<?> signupCompany(@RequestBody SignupRequestDTO signupRequestDTO) {
        // Appeler le service d'authentification pour enregistrer le client
        // authService.signup(signupRequestDTO);
    	if(authService.presentByEmail(signupRequestDTO.getEmail())) {
    		return new ResponseEntity<>("Company already exists with this email ",HttpStatus.NOT_ACCEPTABLE);
    	}
       UserDto createUser=authService.signupCompany(signupRequestDTO);
        return new ResponseEntity<>(createUser,HttpStatus.OK);
    }
    @PostMapping({"/authenticate"})
    public void createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest,HttpServletResponse response) throws IOException, JSONException {
    	try {
    		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
    				authenticationRequest.getUsername(),authenticationRequest.getPassword()	));
    	}catch(BadCredentialsException e) {
    		throw new BadCredentialsException("incorrect username or password!",e);
    }
    	final UserDetails userDetails=userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
    	final String jwt=jwtUtil.generateToken(userDetails.getUsername());
    	User user=userRepository.findByEmail(authenticationRequest.getUsername());
    	response.getWriter().write(new JSONObject()
    			.put("userId", user.getId())
    			.put("role", user.getRole())
    			.toString()
    			);
    	response.addHeader("Access-Control-Expose-Headers", "Authorization");
    	response.addHeader("Access-Control-Allow-Headers","Autorization"+
    	"X-PINGOTHER, Origin, X-Request-With, Content-Type, Accept, X-Custom-header"
    			);
    	response.addHeader(HEADER_STRING, TOKEN_PREFEX+jwt);
}
}