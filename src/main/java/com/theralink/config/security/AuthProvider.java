package com.theralink.config.security;

import com.theralink.common.exception.UnAuthorizeException;
import com.theralink.config.security.jwt.JWTProvider;
import com.theralink.service.user.IUserService;
import com.theralink.utils.HashUtil;
import com.theralink.web.viewModel.user.LoginDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;


@Component
public class AuthProvider implements AuthenticationProvider {
    @Autowired
    private IUserService userDetailsService;

    @Autowired
    private JWTProvider tokenProvider;

    public String authenticateUser(LoginDto entity, HttpServletRequest request) {
        Authentication authentication = authenticate(new UsernamePasswordAuthenticationToken(entity.getUsername(), HashUtil.hashPassword(entity.getPassword())));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return tokenProvider.generateToken(authentication, request);
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String providedUsername = authentication.getPrincipal().toString();
        UserDetails user = userDetailsService.loadUserByUsernameForAuthenticate(providedUsername, null);
        String providedPassword = authentication.getCredentials().toString();
        String correctPassword = user.getPassword();
        if (!providedPassword.equals(correctPassword)) {
            throw new UnAuthorizeException("Incorrect Credentials", 401);
        }
        return new UsernamePasswordAuthenticationToken(user, authentication.getCredentials(), user.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        System.out.println("In MyAuthProvider.supports(): ");
        System.out.println("Checking whether MyAuthProvider supports Authentication type\n");
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

}
