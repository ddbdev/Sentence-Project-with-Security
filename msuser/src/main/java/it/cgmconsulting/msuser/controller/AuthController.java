package it.cgmconsulting.msuser.controller;

import it.cgmconsulting.msuser.entity.Users;
import it.cgmconsulting.msuser.jwt.JwtTokenProvider;
import it.cgmconsulting.msuser.payload.request.SigninRequest;
import it.cgmconsulting.msuser.payload.request.SignupRequest;
import it.cgmconsulting.msuser.payload.response.JwtAuthenticationResponse;
import it.cgmconsulting.msuser.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.text.html.Option;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
public class AuthController {

    @Value("${app.permittedRoles}")
    private List<String> permittedRoles;

    private final UserService userService;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }


    @PostMapping("/auth/signin")
    public ResponseEntity<?> signin(@RequestBody SigninRequest request){

        Optional<Users> foundUser = userService.findByUsername(request.getUsername());

        if (!foundUser.isPresent())
            return new ResponseEntity<String>("Authentication failed", HttpStatus.FORBIDDEN);
        else if (!passwordEncoder.matches(request.getPassword(), foundUser.get().getPassword())) {
            return new ResponseEntity<String>("Authentication failed", HttpStatus.FORBIDDEN);
        }
        else {
            String jwt = JwtTokenProvider.generateToken(foundUser.get(), foundUser.get().getRole());

            JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse(
                    foundUser.get().getId(),
                    foundUser.get().getUsername(),
                    Collections.singleton(foundUser.get().getRole()),
                    jwt
            );

            return new ResponseEntity<>(jwtAuthenticationResponse, HttpStatus.OK);
        }

    }

    @PostMapping("/auth/signup")
    public ResponseEntity<?> signup(@RequestBody SignupRequest request){

        Optional<Users> foundUser = userService.findByUsername(request.getUsername());

        if (!permittedRoles.contains(request.getRole()))
            return new ResponseEntity<>("Role not found, choose between " + permittedRoles, HttpStatus.FORBIDDEN);

        if (foundUser.isPresent())
            return new ResponseEntity<>("Username already registered.", HttpStatus.FORBIDDEN);
        else {
            Users users = new Users();
            users.setUsername(request.getUsername());
            users.setPassword(passwordEncoder.encode(request.getPassword()));
            users.setRole(request.getRole());
            userService.save(users);
        }

        return new ResponseEntity<>("Users registered successfully.", HttpStatus.OK);
    }
}
