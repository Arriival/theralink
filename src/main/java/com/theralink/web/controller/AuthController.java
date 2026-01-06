package com.theralink.web.controller;

import com.theralink.config.security.AuthProvider;
import com.theralink.config.security.jwt.JWTProvider;
import com.theralink.config.security.jwt.JwtResponse;
import com.theralink.service.user.IUserService;
import com.theralink.web.viewModel.user.LoginDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("auth")
public class AuthController {

    @Autowired
    private IUserService iUserService;

    @Autowired
    private AuthProvider authenticationManager;

    @Autowired
    private JWTProvider tokenProvider;

    @PostMapping("/sign-in")
    public ResponseEntity<JwtResponse> authenticateUser(@RequestBody LoginDto entity, HttpServletRequest request) throws AuthenticationException {
        String jwt = authenticationManager.authenticateUser(entity, request);
        return ResponseEntity.ok(new JwtResponse(jwt));
    }

    @GetMapping("/refreshToken")
    public ResponseEntity<JwtResponse> refreshToken(HttpServletRequest request) {
        return ResponseEntity.ok(new JwtResponse(tokenProvider.refreshToken(request)));
    }

    @GetMapping("/sign-out")
    public ResponseEntity signOut(HttpServletRequest request) {
        return ResponseEntity.ok(tokenProvider.clearSession(request));
    }

}
