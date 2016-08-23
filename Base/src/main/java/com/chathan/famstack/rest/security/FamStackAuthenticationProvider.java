package com.chathan.famstack.rest.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import com.chathan.famstack.manager.profile.UserProfileManager;
import com.chathan.famstack.rest.security.login.LoginResult;

@Component("famStackAuthenticationProvider")
public class FamStackAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	UserProfileManager userProfileManager;
	
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        FamStackAuthenticationToken token = (FamStackAuthenticationToken) authentication;
        LoginResult loginResult = null;
        loginResult = userProfileManager.login(token.getName(), token.getPassword());
        if (loginResult != LoginResult.SUCCESS) {
            token.setAuthenticated(false);
        }
        token.setLoginResult(loginResult);
        return token;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(FamStackAuthenticationToken.class);
    }
}
