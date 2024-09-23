package com.hustle_hub.server.controllers;

import com.hustle_hub.server.config.AppConstants;
import com.hustle_hub.server.models.User;
import com.hustle_hub.server.payloads.ApiResponseObject;
import com.hustle_hub.server.payloads.LoginRequestDto;
import com.hustle_hub.server.payloads.LoginResponseDto;
import com.hustle_hub.server.payloads.UserDto;
import com.hustle_hub.server.repositories.UserRepository;
import com.hustle_hub.server.services.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private UserService userService;
    private UserRepository userRepository;
    private ModelMapper modelMapper;
    private AuthenticationManager authenticationManager;
    private final Environment env;

    @Autowired
    public UserController(UserService userService, UserRepository userRepository, ModelMapper modelMapper, AuthenticationManager authenticationManager, Environment env) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.authenticationManager = authenticationManager;
        this.env = env;
    }

    @PostMapping("/create")
    public ApiResponseObject createUser(@RequestBody UserDto userDto){
        return userService.createUser(userDto);
    }

    @PutMapping("/update/{userId}")
    public ApiResponseObject updateUser(@RequestBody UserDto userDto,@PathVariable Long userId){
        return userService.updateUser(userDto,userId);
    }

    @GetMapping("/get/{userId}")
    public ApiResponseObject getUser(@PathVariable Long userId){
        return userService.getUserById(userId);
    }

    @DeleteMapping("/delete/{userId}")
    public ApiResponseObject deleteUser(@PathVariable Long userId){
        return userService.deleteUser(userId);
    }

    @RequestMapping("/")
    public UserDto getUserDetailsAfterLogin(Authentication authentication){
        Optional<User> optionalUser = userRepository.findByEmail(authentication.getName());
        if (optionalUser.isPresent()){
            return modelMapper.map(optionalUser, UserDto.class);
        }
        return null;
    }

    @PostMapping("/apiLogin")
    public ResponseEntity<LoginResponseDto> apiLogin(@RequestBody LoginRequestDto loginRequest){
        String jwt = "";
        Authentication authentication  = UsernamePasswordAuthenticationToken.unauthenticated(loginRequest.username(),loginRequest.password());
        Authentication authenticationResponse =  authenticationManager.authenticate(authentication);
        if (null!=authenticationResponse && authenticationResponse.isAuthenticated()){
            if (null!=env){
                String secret = env.getProperty(AppConstants.JWT_SECRET,AppConstants.JWT_SECRET_DEFAULT_VALUE);
                SecretKey secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
                jwt = Jwts.builder().issuer("Eazy Bank").subject("JWT Token")
                        .claim("username",authentication.getName())
                        .claim("password",authentication.getAuthorities().stream().map(
                                GrantedAuthority::getAuthority).collect(Collectors.joining(",")))
                        .issuedAt(new Date())
                        .expiration(new Date((new Date()).getTime()+30000000))
                        .signWith(secretKey).compact();

            }
        }
        return ResponseEntity.status(HttpStatus.OK).header(AppConstants.JWT_HEADER,jwt)
                .body(new LoginResponseDto(HttpStatus.OK.getReasonPhrase(),jwt)) ;
    }
}
