package com.in28minutes.rest.webservices.restfulwebservices.jwt;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class JwtAuthenticationController {

    private final com.in28minutes.rest.webservices.restfulwebservices.jwt.JwtTokenService tokenService;

    private final AuthenticationManager authenticationManager;

    public JwtAuthenticationController(com.in28minutes.rest.webservices.restfulwebservices.jwt.JwtTokenService tokenService,
                                       AuthenticationManager authenticationManager) {
        this.tokenService = tokenService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<com.in28minutes.rest.webservices.restfulwebservices.jwt.JwtTokenResponse> generateToken(
            @RequestBody com.in28minutes.rest.webservices.restfulwebservices.jwt.JwtTokenRequest jwtTokenRequest) {

        var authenticationToken =
                new UsernamePasswordAuthenticationToken(
                        jwtTokenRequest.username(),
                        jwtTokenRequest.password());

        var authentication =
                authenticationManager.authenticate(authenticationToken);

        var token = tokenService.generateToken(authentication);

        return ResponseEntity.ok(new com.in28minutes.rest.webservices.restfulwebservices.jwt.JwtTokenResponse(token));
    }
}
