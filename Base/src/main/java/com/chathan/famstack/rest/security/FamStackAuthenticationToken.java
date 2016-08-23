package com.chathan.famstack.rest.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import com.chathan.famstack.rest.security.login.LoginResult;

public class FamStackAuthenticationToken extends UsernamePasswordAuthenticationToken {

    public FamStackAuthenticationToken(Object principal, Object credentials) {
		super(principal, credentials, null);
	}

	private static final long serialVersionUID = 1130961461756135458L;
	
    private LoginResult loginResult;

    public String getPassword() {
        Object credentials = getCredentials();
        if (credentials != null) {
            return credentials.toString();
        }
        return null;
    }

    public LoginResult getLoginResult() {
        return loginResult;
    }

    public void setLoginResult(LoginResult loginResult) {
        this.loginResult = loginResult;
    }

	public boolean requiresExternalAuthentication() {
		return false;
	}
}
